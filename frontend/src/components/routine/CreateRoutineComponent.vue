<template>
    <div class="create-routine-template-1">
      <input type="text" class="form-control" placeholder="루틴 이름" v-model="routine.routineName">
      <textarea class="form-control" placeholder="루틴 설명" height="10" v-model="routine.routineDesc"></textarea>
    </div>
    <div class="create-routine-template-2">
      <p class="select-exercise-label">운동 선택</p>
      <div class="selected-routine">
        <h3>선택한 운동</h3>
        <ul>
          <li v-for="exercise in selectedExercises" :key="exercise.exerciseId">
            {{ exercise.exerciseName }}
          </li>
        </ul>
      </div>

      <button class="save-routine btn" @click="saveRoutine()">저장하기</button>

      <div class="recent-searches">
      <h3>최근 검색한 운동</h3>
      <ul>
        <li v-for="exercise in recentSearches" :key="exercise">
          {{ exercise }}
        </li>
      </ul>
    </div>

      <input 
        type="text" 
        class="exercise-search-bar" 
        placeholder="운동명으로 검색" 
        @input="handleInput">
      <div v-for="(exercises, part) in groupedExerciseList" :key="part" class="exercise-group">
        <h3>[ {{ part }} ]</h3>
        <div class="exercise-row">
          <div v-for="(exerciseChunk, index) in chunkArray(exercises, 3)" :key="index" class="exercise-column">
            <div v-for="exercise in exerciseChunk" :key="exercise.exerciseId" class="exercise-item">
              <p @click="addInRoutine(exercise)">{{ exercise.exerciseName }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, computed } from 'vue'
  import axios from 'axios';
  import { useAuthStore } from '@/stores/auth'
  import Cookies from 'js-cookie';

  const user = useAuthStore().user
  
  const routine = ref({
    routineName: '',
    routineDesc: '',
    userId: user.userId
  })

  const exerciseList = ref([])
  const keyword = ref("")
  const selectedRoutine = ref([])


// 쿠키에서 최근 검색한 운동 가져오기
const getRecentSearches = () => {
  const recentSearches = Cookies.get('recentSearches');
  return recentSearches ? JSON.parse(recentSearches) : [];
}

const recentSearches = ref(getRecentSearches());

  // 모든 운동 가져오기
  const getAllExercises = () => {
    axios.get(`http://localhost:8080/exercises/`)
      .then((response) => {
        exerciseList.value = response.data
      })
      .catch((err) => {
        console.log(err);
      });
  }
  
  // 검색어로 운동 검색
  const searchExercises = (keyword) => {
    axios.get(`http://localhost:8080/exercises/search?keyword=${keyword}`)
      .then((response) => {
        exerciseList.value = response.data
      })
      .catch((err) => {
        console.log(err);
      });
  }
  
  // 입력 이벤트 핸들러
  const handleInput = (event) => {
    keyword.value = event.target.value;
    if (keyword.value) {
      searchExercises(keyword.value)
    } else {
      getAllExercises()
    }
  }
  
  // 운동 데이터를 부위별로 그룹화
  const groupedExerciseList = computed(() => {
    return exerciseList.value.reduce((acc, exercise) => {
      if (!acc[exercise.exercisePart]) {
        acc[exercise.exercisePart] = [];
      }
      acc[exercise.exercisePart].push(exercise);
      return acc;
    }, {});
  });
  
  // 배열을 주어진 크기로 나누기
  const chunkArray = (array, chunkSize) => {
    const result = [];
    for (let i = 0; i < array.length; i += chunkSize) {
      result.push(array.slice(i, i + chunkSize));
    }
    return result;
  }
  
  // 선택한 운동을 리스트에 넣거나 제거하기
  const addInRoutine = (exercise) => {
    const index = selectedRoutine.value.findIndex(e => e.exerciseId === exercise.exerciseId);
    if (index === -1) {
      selectedRoutine.value.push(exercise);
    } else {
      selectedRoutine.value.splice(index, 1);
    }
  }
  
  // 선택한 운동의 상세 정보 가져오기
  const selectedExercises = computed(() => {
    return selectedRoutine.value.map(exercise => exercise);
  });

  //루틴 저장하기
  const saveRoutine = () => {
    //먼저 루틴 등록하기
    axios.post('http://localhost:8080/routines/', routine.value)
    .then((response) => {
        saveExercisesInRoutine(response.data.routineId)
    })
    .catch((err) => {

    });
  }

    // 루틴에 운동 저장하기
    const saveExercisesInRoutine = (routineId) => {
    axios.post(`http://localhost:8080/exercises/${routineId}`, selectedRoutine.value)
        .then(() => {
        console.log("운동이 루틴에 성공적으로 저장되었습니다.");
        })
        .catch((err) => {
        console.log("운동 저장 중 오류 발생:", err);
        });
    }
  
  onMounted(() => {
    getAllExercises();
  });
  </script>
  
  <style>
  .exercise-group {
    margin-bottom: 20px;
  }
  
  .exercise-row {
    display: flex;
    flex-wrap: wrap;
    margin-left: -10px;
    margin-right: -10px;
  }
  
  .exercise-column {
    flex: 1 0 33%;
    box-sizing: border-box;
    padding-left: 10px;
    padding-right: 10px;
  }
  
  .exercise-item {
    margin-bottom: 10px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .exercise-item p {
    margin: 0;
  }
  
  .exercise-item:hover {
    background-color: #f0f0f0;
    border-radius: 5px;
  }
  
  .selected-routine {
    margin-bottom: 20px;
  }
  
  .selected-routine ul {
    list-style-type: none;
    padding-left: 0;
  }
  
  .selected-routine li {
    background-color: #f0f0f0;
    margin-bottom: 5px;
    padding: 10px;
    border-radius: 5px;
  }
  </style>
  