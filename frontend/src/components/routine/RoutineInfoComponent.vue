<template>
    <p class="routine-info-title">나의 루틴정보</p>
    <button class="create-routine"><RouterLink :to="{ name :'CreateRoutineComponent'}">루틴만들기</RouterLink></button>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRoutineStore } from '@/stores/routine';
import { RouterLink } from 'vue-router';

const routineStore = useRoutineStore()

// onMounted(() => {
//     routineStore.getMyRoutines()
// })
</script>