<template>
    <div class="create-routine-template-1">
        <input type="text" class="form-control" placeholder="루틴 이름">
        <textarea class="form-control" placeholder="루틴 설명" height="10"></textarea>
    </div>
    <div class="create-routine-template-2">
        <p class="select-exercise-label">운동 선택</p>
        <input type="text" class="exercise-search-bar" placeholder="운동명으로 검색" v-model="keyword">
        <!-- <SearchExerciseResultComponent/> -->
        <div class="exercises" v-for="exercise in exerciseList">
            <p><em>[ {{ exercise.exercisePart }} ]</em>{{ exercise.exerciseName }}</p>
        </div>
    </div>
</template>

<script setup>
import SearchExerciseResultComponent from './SearchExerciseResultComponent.vue';
import { ref, onMounted } from 'vue'
import axios from 'axios';

const exerciseList = ref([])
const selectedExercise = ref([])


//모든 운동 가져오기
const getAllExercises = () => {
    axios.get(`http://localhost:8080/exercises/`)
    .then((response) => {
        exerciseList.value = response.data
        console.log(exerciseList)
    })
    .catch((err) => {
      console.log(err);
    });
}

//파트 별 운동 가져오기
const getAllExercisesByPart = () => {
    //상체
    axios.get('http://localhost:8080/exercises/search/'+`상체`)
    .then((response) => {
        console.log(response.data)
    })
}

//검색한 운동 가져오기


onMounted(() => {
    getAllExercisesByPart();
})

</script>
