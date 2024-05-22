<template>
    <div id="app">
      <form class="form">
        <p class="title">스트릭 변경권 가격 뽑기</p>
        <p class="message">나온 숫자 3개의 합이 최종 가격이 됩니다.</p>
        <div class="slots">
          <div class="slot" v-for="(slot, index) in slots" :key="index">
            <div
              class="number"
              v-for="(number, i) in slot.numbers.slice(0, 3)"
              :key="i"
            >
              {{ number }}
            </div>
          </div>
        </div>
        <button class="submit" @click.prevent="toggleSlots">
          {{ isSpinning ? "STOP" : "START" }}
        </button>
        <div class="price">
          <h4>스트릭 변경권 가격 <br /></h4>
          <h2>{{ sum }}</h2>
        </div>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        slots: [
          {
            numbers: [],
            interval: null,
          },
          {
            numbers: [],
            interval: null,
          },
          {
            numbers: [],
            interval: null,
          },
        ],
        isSpinning: false,
        sum: 0,
      };
    },
    methods: {
      shuffle(array) {
        for (let i = array.length - 1; i > 0; i--) {
          const j = Math.floor(Math.random() * (i + 1));
          [array[i], array[j]] = [array[j], array[i]];
        }
        return array;
      },
      toggleSlots() {
        if (this.isSpinning) {
          this.stopSlots();
        } else {
          this.startSlots();
        }
      },
      startSlots() {
        this.isSpinning = true;
        this.sum = 0;
        const intervalSpeed = 50;
        this.slots.forEach((slot) => {
          slot.numbers = this.shuffle(Array.from({ length: 10 }, (_, i) => i));
          slot.interval = setInterval(() => {
            slot.numbers.push(slot.numbers.shift());
          }, intervalSpeed);
        });
      },
      stopSlots() {
        this.slots.forEach((slot) => {
          clearInterval(slot.interval);
        });
        this.isSpinning = false;
        this.calculateSum();
      },
      calculateSum() {
        this.sum = this.slots.reduce((acc, slot) => {
          return acc + slot.numbers[0];
        }, 0);
      },
    },
  };
  </script>
  
  <style scoped>
  #app {
    text-align: center;
    margin-top: 40px;
  }
  
  .form {
    display: flex;
    flex-direction: column;
    gap: 10px;
    max-width: 350px;
    background-color: #fff;
    padding: 20px;
    border-radius: 20px;
    position: relative;
    margin: 0 auto;
  }
  
  .title {
    font-size: 26px;
    color: #3c90e2;
    font-weight: 600;
    letter-spacing: -1px;
    position: relative;
    display: flex;
    align-items: center;
    padding-left: 30px;
  }
  
  .title::before,
  .title::after {
    position: absolute;
    content: "";
    height: 16px;
    width: 16px;
    border-radius: 50%;
    left: 0px;
    background-color: #3c90e2;
  }
  
  .title::before {
    width: 18px;
    height: 18px;
    background-color: #3c90e2;
  }
  
  .title::after {
    width: 18px;
    height: 18px;
    animation: pulse 1s linear infinite;
  }
  
  .message,
  .price {
    color: rgba(88, 87, 87, 0.822);
    font-size: 14px;
  }
  
  .price {
    text-align: center;
  }
  
  .price a {
    color: #3c90e2;
  }
  
  .price a:hover {
    text-decoration: underline #3c90e2;
  }
  
  .submit {
    border: none;
    outline: none;
    background-color: #3c90e2;
    padding: 10px;
    border-radius: 10px;
    color: #fff;
    font-size: 16px;
    transform: 0.3s ease;
    cursor: pointer;
  }
  
  .submit:hover {
    background-color: #3c90e2;
  }
  
  @keyframes pulse {
    from {
      transform: scale(0.9);
      opacity: 1;
    }
    to {
      transform: scale(1.8);
      opacity: 0;
    }
  }
  
  .slots {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
  }
  
  .slot {
    display: flex;
    flex-direction: column;
    margin: 0 10px;
    width: 4px;
    height: 46px;
    overflow: hidden;
    border-radius: 0.4em;
    box-shadow: 0.1em 0.1em;
    font-weight: 900;
    font-size: 18px;
    color: white;
    background: royalblue;
    font-family: inherit;
    padding: 0.1em 1.3em;
  }
  
  .number {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 40px;
    font-size: 24px;
    font-weight: bold;
    background-color: #fff;
    border-bottom: 1px solid #ddd;
  }
  </style>