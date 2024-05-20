<template>
    <div class="calendar" id="calendar">
      <div
        v-for="(day, index) in days"
        :key="index"
        class="day"
        :class="{ filled: isFilled(day.dateStr) }"
        :style="{ gridColumnStart: getColumn(index), gridRowStart: getRow(index) }"
      >
        <div class="tooltip">{{ day.dateStr }}</div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "CalendarGrid",
    data() {
      return {
        records: [
          { id: 41, user_id: 41, board_id: 48, title: '나다', date: '2024-05-20' },
          { id: 40, user_id: 41, board_id: 10, title: 'Outdoor workout', date: '2024-05-18' },
          { id: 39, user_id: 41, board_id: 9, title: 'Gym day!', date: '2024-05-17' },
          { id: 38, user_id: 41, board_id: 8, title: 'Healthy lifestyle', date: '2024-05-16' },
          { id: 37, user_id: 41, board_id: 7, title: 'Workout tips', date: '2024-05-14' },
          { id: 36, user_id: 41, board_id: 6, title: 'My favorite exercises', date: '2024-05-12' },
          { id: 35, user_id: 41, board_id: 5, title: 'Here\'s my progress', date: '2024-05-11' },
          { id: 34, user_id: 41, board_id: 4, title: 'Feeling great after this workout', date: '2024-05-10' },
          { id: 33, user_id: 41, board_id: 3, title: 'Loving the new exercises', date: '2024-05-08' },
          { id: 32, user_id: 41, board_id: 2, title: 'Just finished a tough session', date: '2024-05-07' },
          { id: 31, user_id: 41, board_id: 1, title: 'Check out my new workout routine!', date: '2024-05-06' },
          { id: 30, user_id: 41, board_id: 10, title: '야외에서의 운동!', date: '2024-05-05' },
          { id: 29, user_id: 41, board_id: 9, title: '체육관에서의 하루!', date: '2024-05-04' }
        ],
        startDate: new Date(2024, 0, 1), // January 1, 2024
        daysToDisplay: 366, // Leap year
        days: [],
      };
    },
    created() {
      const oneDay = 24 * 60 * 60 * 1000;
  
      for (let i = 0; i < this.daysToDisplay; i++) {
        const currentDate = new Date(this.startDate.getTime() + i * oneDay);
        const dateStr = currentDate.toISOString().split('T')[0];
        this.days.push({ dateStr });
      }
    },
    methods: {
      isFilled(dateStr) {
        return this.records.some(record => record.date === dateStr);
      },
      getColumn(index) {
        return Math.floor(index / 7) + 1;
      },
      getRow(index) {
        return (index % 7) + 1;
      }
    }
  };
  </script>
  
  <style scoped>
  .calendar {
    display: grid;
    grid-template-columns: repeat(auto-fill, 15px); /* 고정된 박스 크기 */
    grid-template-rows: repeat(7, 15px); /* 7행으로 설정 */
    gap: 4px; /* 동일한 행간 및 열 간격 */
  }
  .day {
    width: 15px; /* 고정된 박스 크기 */
    height: 15px; /* 고정된 박스 크기 */
    border: 0.3px solid grey; /* 줄어든 테두리 */
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
  }
  .filled {
    background-color: pink;
  }
  .tooltip {
    display: none;
    position: absolute;
    bottom: 125%;
    left: 50%;
    transform: translateX(-50%);
    background-color: #333;
    color: #fff;
    padding: 5px;
    border-radius: 3px;
    font-size: 12px;
    white-space: nowrap;
    z-index: 10;
  }
  .day:hover .tooltip {
    display: block;
  }
  .calendar {
  overflow: visible;
}
  </style>
  