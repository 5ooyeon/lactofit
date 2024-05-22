<template>
    <p class="routine-info-title">나의 루틴정보</p>
    <div class="routine-list-container">
      <div class="routine-list">
        <div v-for="routine in myRoutines" :key="routine.routineId" class="form-check">
          <input
            class="form-check-input"
            type="radio"
            name="routine"
            :id="`routine${routine.routineId}`"
            :value="routine.routineId"
            v-model="writeBoard.routineId"
          />
          <label class="form-check-label" :for="`routine${routine.routineId}`">
            {{ routine.routineName }}
            <ul>
              <li v-for="exercise in routine.exercises" :key="exercise.exerciseId">
                {{ exercise.exerciseName }}
              </li>
            </ul>
          </label>
        </div>
      </div>
    </div>
    <button class="create-routine">
      <RouterLink :to="{ name: 'CreateRoutineComponent' }">루틴만들기</RouterLink>
    </button>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useAuthStore } from '@/stores/auth';
  import { RouterLink } from 'vue-router';
  import axios from 'axios';
  
  const writeBoard = ref({
    routineId: null,
  });
  
  const myRoutines = ref([]);
  onMounted(() => {
    axios
      .get(`http://localhost:8080/routines/users/${useAuthStore().user.userId}`)
      .then((response) => {
        console.log(response.data);
        myRoutines.value = response.data;
        console.log(myRoutines.value);
      })
      .catch((err) => {
        console.error('Error fetching routines:', err);
      });
  });
  </script>
  
  <style scoped>
  /* 스타일 정의 */
  </style>
  