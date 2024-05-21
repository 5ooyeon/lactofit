<template>
  <div class="container">
    <h1>운동 정보 검색</h1>
    <div id="answerbox" v-if="isLoading || response">
      <h3 class="selected-exercise">{{ selectedExercise }}</h3>
      <div v-if="isLoading" class="loader">
        <span class="l">L</span>
        <span class="o">A</span>
        <span class="a">C</span>
        <span class="d">T</span>
        <span class="i">O</span>
        <span class="n">F</span>
        <span class="g">I</span>
        <span class="d1">T</span>
        <span class="d2">!</span>
      </div>
      <div v-else>
        {{ response }}
      </div>
    </div>
    <div class="button-container" v-if="response && !isLoading">
      <RouterLink class="go-to-routine" type="button" :to="{name: 'CreateRoutineComponent'}">
        루틴 만들러 가기
      </RouterLink>
      <button class="go-to-routine" @click="goYoutube()">관련 유튜브 영상 보기</button>
    </div>
    <input 
      type="text" 
      class="exercise-search-bar" 
      placeholder="운동명으로 검색" 
      @input="handleInput"
      ref="searchInput">
    <div v-for="(exercises, part) in groupedExerciseList" :key="part" class="exercise-group">
      <h3>[ {{ part }} ]</h3>
      <div class="exercise-grid">
        <div v-for="exercise in exercises" :key="exercise.exerciseId" class="exercise-item">
          <p @click="searchExercise(exercise.exerciseName)">{{ exercise.exerciseName }}</p>
        </div>
      </div>
    </div>
  </div>
</template>



<script setup>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios';
import { useAuthStore } from '@/stores/auth';
import {useYoutubeStore} from '@/stores/youtube'
import { useRouter } from 'vue-router';
import Cookies from 'js-cookie'

const openaiApiKey = import.meta.env.VITE_OPENAI_API_KEY;

const user = useAuthStore().user
const youtubeStore = useYoutubeStore();
const router = useRouter();

const routine = ref({
  routineName: '',
  routineDesc: '',
  userId: user.userId
})

const exerciseList = ref([])
const keyword = ref("")
const response = ref("")
const selectedRoutine = ref([])
const isPressed = ref(false)
const selectedExercise = ref("")
const isLoading = ref(false)
const searchInput = ref('')

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

// 쿠키에 운동명 저장
const saveToCookies = (exerciseName) => {
  let recentSearches = Cookies.get('recentSearches');
  recentSearches = recentSearches ? JSON.parse(recentSearches) : [];
  if (!recentSearches.includes(exerciseName)) {
    recentSearches.push(exerciseName);
    if (recentSearches.length > 10) {
      recentSearches.shift(); // 최근 10개만 유지
    }
    Cookies.set('recentSearches', JSON.stringify(recentSearches), { expires: 7 });
  }
}

// 운동에 대한 설명 검색
const searchExercise = async (exerciseName) => {
  selectedExercise.value = exerciseName
  isLoading.value = true; // 로딩 상태 시작
  window.scrollTo({ top: 0, behavior: 'smooth' }); // 페이지 상단으로 스크롤
  searchInput.value.value = '';
  saveToCookies(exerciseName)
  getAllExercises()
  try {
    const prompt = `[${exerciseName}] 라는 운동에 대해 한국어와 존댓말로 설명해줘. 다음은 내가 원하는 답변의 형태 예시야.

    펙 덱 플라이는 가슴근육과 삼각근을 강화하는 운동입니다. 이 운동은 펙 덱 머신을 이용하여 수행됩니다. 

1단계: 먼저 펙 덱 머신에 앉은 후, 손잡이를 잡고 팔을 뻗어줍니다.
2단계: 가슴을 바닥에 수평하게 유지하면서 손을 몸 쪽으로 모으는 운동을 시작합니다.
3단계: 가슴을 최대한 벌리며 손을 다시 처음 자세로 돌아갑니다.
4단계: 숨을 내쉬면서 가동 범위를 최대한 확보합니다.
5단계: 팔을 뻗으면서 다시 시작 자세로 돌아옵니다.

이 운동을 10-15회 반복하고 3-4 세트를 수행하면 좋습니다. 근육에 무리가 가지 않도록 필요에 따라 중량을 조절해가며 운동을 하시기 바랍니다.

위는 내가 원하는 답변의 예시야
    `;
    const result = await fetchGPT35Response(prompt);
    response.value = result;
    // console.log(response.value);
    isLoading.value = false; // 로딩 상태 종료
    

  } catch (error) {
    if (error.response && error.response.status === 429) {
      response.value = "요청이 너무 많습니다. 잠시 후 다시 시도해주세요.";
      console.error("Too many requests - status code 429");
      await delay(3000); // 3초 대기 후 재시도
      searchExercise(exerciseName); // 재시도
    } else {
      response.value = "에러 발생";
      console.error(error);
    }
  }
};

const fetchGPT35Response = async (prompt) => {
  const response = await axios.post(
    "https://api.openai.com/v1/chat/completions",
    {
      model: "gpt-3.5-turbo",
      messages: [{ role: "user", content: prompt }],
    },
    {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${openaiApiKey}`,
      },
    }
  );

  return response.data.choices[0].message.content;
};

const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

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

//검색한 운동과 유튜브 이동 메소드
const goYoutube = async () => {
  const keyword1 = selectedExercise.value;
  const search1 = 'search'
  await youtubeStore.getVideosBySearchWord(keyword1, search1);
  router.push({ name: 'SearchResultComponent' });
}

onMounted(getAllExercises);
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

.exercise-search-bar {
  width: 80%; /* 검색창 너비 지정 */
  padding: 10px; /* 내부 여백 추가 */
  margin: 20px auto; /* 외부 여백 추가 및 중앙 정렬 */
  border-radius: 5px; /* 테두리 둥글게 */
  border: 1px solid #ccc; /* 테두리 스타일 지정 */
}

.exercise-group {
  margin-top: 30px; /* 각 그룹 간격 추가 */
}

.exercise-grid {
  display: flex; /* Flexbox 사용 */
  flex-wrap: wrap; /* 아이템들을 여러 줄로 배치 */
  justify-content: center; /* 중앙 정렬 */
}

.exercise-item {
  flex: 1 1 30%; /* 아이템의 기본 크기 지정 및 늘어나거나 줄어들 수 있게 설정 */
  margin: 10px; /* 아이템 간 간격 추가 */
  padding: 10px; /* 내부 여백 추가 */
  background-color: #f0f0f0; /* 배경색 지정 */
  border-radius: 5px; /* 테두리 둥글게 */
  cursor: pointer; /* 마우스 커서 포인터로 변경 */
  transition: background-color 0.3s ease; /* 배경색 변경 애니메이션 추가 */
}

.exercise-item:hover {
  background-color: #e0e0e0; /* 마우스 오버 시 배경색 변경 */
}

.l {
  color: blue;
  opacity: 0;
  animation: pass 2s ease-in-out infinite;
  animation-delay: 0.2s;
  letter-spacing: 0.5em;
  font-weight: bold;
  text-shadow: 2px 2px 3px #919191;
}

.o {
  color: blue;
  opacity: 0;
  animation: pass 2s ease-in-out infinite;
  animation-delay: 0.4s;
  letter-spacing: 0.5em;
  font-weight: bold;
  text-shadow: 2px 2px 3px #919191;
}

.a {
  color: blue;
  opacity: 0;
  animation: pass 2s ease-in-out infinite;
  animation-delay: 0.6s;
  letter-spacing: 0.5em;
  font-weight: bold;
  text-shadow: 2px 2px 3px #919191;
}

.d {
  color: blue;
  opacity: 0;
  animation: pass 2s ease-in-out infinite;
  animation-delay: 0.8s;
  letter-spacing: 0.5em;
  font-weight: bold;
  text-shadow: 2px 2px 3px #919191;
}

.i {
  color: blue;
  opacity: 0;
  animation: pass 2s ease-in-out infinite;
  animation-delay: 1s;
  letter-spacing: 0.5em;
  font-weight: bold;
  text-shadow: 2px 2px 3px #919191;
}

.n {
  color: blue;
  opacity: 0;
  animation: pass 2s ease-in-out infinite;
  animation-delay: 1.2s;
  letter-spacing: 0.5em;
  font-weight: bold;
  text-shadow: 2px 2px 3px #919191;
}

.g {
  color: blue;
  opacity: 0;
  animation: pass 2s ease-in-out infinite;
  animation-delay: 1.4s;
  letter-spacing: 0.5em;
  font-weight: bold;
  text-shadow: 2px 2px 3px #919191;
}

.d1 {
  color: blue;
  opacity: 0;
  animation: pass1 2s ease-in-out infinite;
  animation-delay: 1.6s;
  letter-spacing: 0.5em;
  font-weight: bold;
  text-shadow: 2px 2px 3px #919191;
}

.d2 {
  color: blue;
  opacity: 0;
  animation: pass1 2s ease-in-out infinite;
  animation-delay: 2s;
  letter-spacing: 0.5em;
  font-weight: bold;
  text-shadow: 2px 2px 3px #919191;
}

@keyframes pass {
  0% {
    opacity: 1;
  }

  50% {
    opacity: 0;
  }

  100% {
    opacity: 1;
  }
}

@keyframes pass1 {
  0% {
    opacity: 1;
  }

  50% {
    opacity: 0;
  }

  100% {
    opacity: 1;
  }
}

.button-container {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
  max-width: 800px;
  margin-left: auto; /* 수평 중앙 정렬 */
  margin-right: auto; /* 수평 중앙 정렬 */
}

.go-to-routine, .go-to-youtube {
  width: 49%;
  padding: 10px;
  border: 0.5px solid black;
  border-radius: 5px;
  background-color: #747474; /* 녹색 배경 */
  text-decoration: none;
  text-align: center;
  color: white; /* 흰색 글자 */
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.go-to-routine:hover, .go-to-youtube:hover {
  background-color: rgb(59, 59, 59); /* 호버 시 더 진한 녹색 */
}



</style>
