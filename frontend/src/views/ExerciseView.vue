<template>
    <div id="app">
      <h1>운동 정보 검색</h1>
      <div id="answerbox" v-if="response">ChatGPT: {{ response }}</div>
      <h2>운동 목록</h2>
      <div id="exercise-list">
        <ul>
          <li
            v-for="exercise in exercises"
            :key="exercise.exerciseId"
            @click="searchExercise(exercise.exerciseName)"
            @mousedown="isPressed = true"
            @mouseup="isPressed = false"
            :class="{ pressed: isPressed }"
          >
            {{ exercise.exerciseName }}
          </li>
        </ul>
      </div>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from "vue";
  import { fetchGPT35Response } from "./api/openai";
  import { fetchExercises } from "./api/exercise";
  
  export default {
    name: "App",
    setup() {
      const response = ref("");
      const exercises = ref([]);
      const isPressed = ref(false);
      const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
  
      const searchExercise = async (exerciseName) => {
        response.value = "저는 실수를 할 수 있습니다.";
        try {
          const prompt = `${exerciseName} 운동을 한국어로 설명해줘`;
          const result = await fetchGPT35Response(prompt);
          response.value = result;
          console.log(response.value);
        } catch (error) {
          if (error.response && error.response.status === 429) {
            response.value = "요청이 너무 많습니다. 잠시 후 다시 시도해주세요.";
            console.error("Too many requests - status code 429");
            await delay(3000); // 10초 대기 후 재시도
            searchExercise(exerciseName); // 재시도
          } else {
            response.value = "에러 발생";
            console.error(error);
          }
        }
      };
  
      const loadExercises = async () => {
        try {
          exercises.value = await fetchExercises();
        } catch (error) {
          console.error("에러 발생", error);
        }
      };
  
      onMounted(loadExercises);
  
      return {
        response,
        searchExercise,
        exercises,
        isPressed,
      };
    },
  };
  </script>
  
  <style scoped>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    text-align: center;
    margin-top: 60px;
  }
  
  #answerbox {
    border: 1px solid black; /* 테두리 두께, 스타일, 색상 지정 */
    padding: 10px; /* 내부 여백 추가 */
    margin-top: 20px; /* 위쪽 여백 추가 */
    background-color: #f9f9f9; /* 배경색 추가 */
    border-radius: 5px; /* 테두리 둥글게 */
    overflow-y: auto; /* 높이를 초과할 경우 스크롤 추가 */
    white-space: pre-wrap; /* 줄바꿈 유지 */
    display: block; /* 블록 요소로 표시 */
    max-width: 800px; /* 최대 너비 지정 */
    margin-left: auto; /* 수평 중앙 정렬 */
    margin-right: auto; /* 수평 중앙 정렬 */
    height: auto; /* 높이를 자동으로 설정 */
    max-height: 600px; /* 최대 높이 지정 */
  }
  
  #exercise-list {
    border: 1px solid black; /* 테두리 두께, 스타일, 색상 지정 */
    padding: 10px; /* 내부 여백 추가 */
    margin-top: 20px; /* 위쪽 여백 추가 */
    background-color: #f9f9f9; /* 배경색 추가 */
    border-radius: 5px; /* 테두리 둥글게 */
    max-width: 800px; /* 최대 너비 지정 */
    margin-left: auto; /* 수평 중앙 정렬 */
    margin-right: auto; /* 수평 중앙 정렬 */
  }
  
  #exercise-list ul {
    list-style-type: none; /* 리스트 아이템의 기본 불릿 제거 */
    padding: 0; /* 기본 패딩 제거 */
  }
  
  #exercise-list li {
    padding: 10px; /* 내부 여백 추가 */
    margin-bottom: 5px; /* 리스트 아이템 간 간격 추가 */
    cursor: pointer; /* 마우스 커서 포인터로 변경 */
    transition: background-color 0.3s ease; /* 배경색 변경 애니메이션 추가 */
    border-radius: 5px; /* 테두리 둥글게 */
  }
  
  #exercise-list li:hover {
    background-color: #e0e0e0; /* 마우스 오버 시 배경색 변경 */
  }
  
  #exercise-list li.pressed {
    background-color: #d0d0d0; /* 클릭 시 배경색 변경 */
  }
  </style>
  