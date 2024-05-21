<template>
  <div class="activity-calendar">
    <div class="calendar">
      <div class="calendar-grid">
        <div v-for="week in weeks" :key="week[0] ? week[0].toISOString() : Math.random()" class="week">
          <div
            v-for="day in week"
            :key="day ? day.toISOString() : Math.random()"
            :title="getTitle(day)"
            :class="['day', getColor(day), isToday(day) ? 'today' : '']"
          ></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { getYearWeeks, getDayOfYear, formatDate } from '../../utils/date';
import { useAuthStore } from '@/stores/auth';
import axios from 'axios';

const year = ref(new Date().getFullYear());
const records = ref([]);

const myRoutines = () => {
  const userId = useAuthStore().user.userId;

  axios.get('http://localhost:8080/boards/user/'+userId)
  .then((response) => {
    records.value = response.data
    // console.log(records.value)
  })
}

const weeks = ref(getYearWeeks(year.value));

const contributions = computed(() => {
  const contributionsMap = {};

  records.value.forEach(record => {
    const date = new Date(record.board.boardRegDate);
    const dayOfYear = getDayOfYear(date);
    if (!contributionsMap[dayOfYear]) {
      contributionsMap[dayOfYear] = [];
    }
    contributionsMap[dayOfYear].push(record);
  });

  return contributionsMap;
});

function getColor(date) {
  if (!date) return 'color-0';
  const dayOfYear = getDayOfYear(date);
  const contributionCount = contributions.value[dayOfYear]?.length || 0;
  if (contributionCount >= 1) return 'color-1';
  return 'color-0';
}

function getTitle(date) {
  if (!date) return '';
  const dayOfYear = getDayOfYear(date);
  // console.log(dayOfYear)
  const contributionCount = contributions.value[dayOfYear]?.length || 0;
  return `${formatDate(date)}`;
}

function isToday(date) {
  if (!date) return false;
  const today = new Date(new Date().toLocaleString('en-US', { timeZone: 'Asia/Seoul' }));

  return (
    date.getDate() === today.getDate() &&
    date.getMonth() === today.getMonth() &&
    date.getFullYear() === today.getFullYear()
  );
}

function isYear(date) {
  if(!date) return false;
  // console.log(date.getFullYear())
  return (
    date.getFullYear() === '2024'
  )
}

onMounted(() => {
  myRoutines();
});
</script>

<style scoped>
.activity-calendar {
  text-align: center;
}

.calendar {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.week-row {
  display: flex;
  justify-content: center;
}

.day-label {
  width: 15px;
  height: 15px;
  margin: 1px;
  text-align: center;
}

.calendar-grid {
  display: flex;
  flex-direction: row;
}

.week {
  display: flex;
  flex-direction: column;
  margin: 0.5px;
}

.day {
  width: 14px;
  height: 14px;
  margin: 0.5px;
  border-radius: 3px;
}

.color-0 {
  background-color: #ebedf0;
}

.color-1 {
  background-color: pink;
}

.today {
  border: 2px solid rgb(255, 0, 98);
}
</style>
