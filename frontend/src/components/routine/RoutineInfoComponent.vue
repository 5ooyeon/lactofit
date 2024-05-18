<template>
    <p class="routine-info-title">나의 루틴정보</p>

</template>

<script setup>
import { onMounted } from 'vue';
import { useRoutineStore } from '@/stores/routine';

const routineStore = useRoutineStore()

onMounted(() => {
    routineStore.getMyRoutines()
})
</script>