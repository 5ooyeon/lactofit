<!-- <template>
    <div>
        <video src="https://kr.object.ncloudstorage.com/thebody/hit35_main_width_240326.mp4" autoplay playsinline class="main-video" loop ></video>
    </div>
</template>

<style scoped>
.main-video {
    width: 100%;
    height: 100%;
    object-fit: cover;
    display: block;
}
</style> -->

<template>
    <div>
      <main>
        <section class="home section">
          <video src="https://kr.object.ncloudstorage.com/thebody/hit35_main_width_240326.mp4" autoplay loop muted></video>
          <div class="home__content">
            <p class="home_title">
              운동이 쉬워진다.<br />
              싸핏으로<br />
              운동 고민 끝
            </p>
            <button class="cssbuttons-io">
              <span> 시작하기 </span>
            </button>
          </div>
        </section>
  
        <!--==================== routine ====================-->
        <section class="routine section" id="routine">
          <div class="routine__container container">
            <div class="routine__content">
              <div class="routine__image">
                <img src="https://images.squarespace-cdn.com/content/v1/6581fd2d9a7c875e912e0907/19116ab1-4f67-4632-851b-8f0cf510b417/Sleek+Objects+1.jpg?format=2500w" alt="new image" />
              </div>
              <div class="routine__info">
                <h2 class="routine__title">More about our brand</h2>
                <p class="routine__description">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum id amet incidunt deleniti nesciunt, possimus laborum minus commodi! Ratione odit voluptate iure dolorum libero sit cumque sapiente animi dicta
                </p>
                <a href="#" class="routine__button">Learn More</a>
              </div>
            </div>
          </div>
        </section>
  
        <!--==================== youtube ====================-->
        <section class="routine section" id="youtube">
          <div class="routine__container container">
            <div class="routine__content">
              <div class="routine__info">
                <h2 class="routine__title">
                  운동 시 자세 잡는 것이 어렵다면?<br />
                  운동 별 영상을 제공해드려요.
                </h2>
                <p class="routine__description">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellat, hic ad adipisci dolores debitis eligendi asperiores earum perferendis deserunt repellendus eveniet, laborum magnam mollitia animi quia velit, enim sapiente. Inventore!
                </p>
                <button class="youtube__button"><RouterLink :to="{name:'YoutubeMainComponent'}">영상 찾아보기</RouterLink></button>
              </div>
              <div class="routine__image">
                <img src="https://images.squarespace-cdn.com/content/v1/6581fd2d9a7c875e912e0907/19116ab1-4f67-4632-851b-8f0cf510b417/Sleek+Objects+1.jpg?format=2500w" alt="new image" />
              </div>
            </div>
          </div>
        </section>
  
        <!--==================== social ====================-->
        <section class="social section" id="social">
          <h2 class="social__title">
            “오늘만 운동 쉴까"<br />
            함께 이겨내요.
          </h2>
          <p class="social__description">
            SSAFIT 커뮤니티에서 소통하고, 공유하고, 응원하세요.
          </p>
  
          <div class="social__container container grid">
            <div class="social__card">
              <img src="https://picsum.photos/200" alt="social image" class="social__img" />
            </div>
            <div class="social__card">
              <img src="https://picsum.photos/200" alt="social image" class="social__img" />
            </div>
            <div class="social__card">
              <img src="https://picsum.photos/200" alt="social image" class="social__img" />
            </div>
            <div class="social__card">
              <img src="https://picsum.photos/200" alt="social image" class="social__img" />
            </div>
          </div>
  
          <div class="social__button-container container">
            <a href="#" class="social__button">더 많은 게시글 보러가기</a>
          </div>
        </section>
      </main>
  
      <!-- Scroll to Top Button -->
      <button id="scrollToTopBtn" class="scroll-top-button">
        <div class="top-button-box">
          <span class="top-button-elem">
            <svg viewBox="0 0 46 40" xmlns="http://www.w3.org/2000/svg">
              <path d="M23 5l-17 17c-1.1 1.1-1.1 3.1 0 4.2s3.1 1.1 4.2 0L23 14.2l13.8 13.8c1.1 1.1 3.1 1.1 4.2 0s1.1-3.1 0-4.2L23 5z"></path>
            </svg>
          </span>
          <span class="top-button-elem">
            <svg viewBox="0 0 46 40">
              <path d="M23 5l-17 17c-1.1 1.1-1.1 3.1 0 4.2s3.1 1.1 4.2 0L23 14.2l13.8 13.8c1.1 1.1 3.1 1.1 4.2 0s1.1-3.1 0-4.2L23 5z"></path>
            </svg>
          </span>
        </div>
      </button>
  
      <footer class="footer">
        <div class="container">
          <div class="footer__content">
            <div class="footer__left">
              <h2 class="footer__title">SSAFIT LACTOFIT</h2>
              <p>Lee Dongyeop, Cho Sooyeon</p>
            </div>
            <div class="footer__right">
              <div class="footer__info">
                <h3 class="footer__info-title">Location</h3>
                <p>06220<br />서울 강남구 테헤란로 212 205호 왼쪽 뒤에서 두번째</p>
              </div>
              <div class="footer__info">
                <h3 class="footer__info-title">Contact</h3>
                <p><a href="mailto:ssafitlactofit@gmail.com">ssafitlactofit@gmail.com</a></p>
              </div>
            </div>
          </div>
        </div>
      </footer>
    </div>
  </template>
  
  <script setup>
  
  </script>
  
  <style scoped>

/* Video and Overlay Styling */
.home {
    position: relative;
    width: 100vw; /* Video matches viewport width */
    height: 100vh; /* Video matches viewport height */
    overflow: hidden;
    margin-top: 0;
    padding-top: 0;
}

video {
    width: 100%;
    height: 100%;
    object-fit: cover;
    position: absolute; /* Ensure video stays in place */
    top: 0;
    left: 0;
    z-index: -1; /* Video behind content */
}

/* Overlay Styling */
.home::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5); /* Adjust transparency as needed */
    z-index: 0; /* Ensure overlay is on top of video but below content */
}

.home__content {
    position: absolute; /* Ensure content is above overlay */
    z-index: 1; /* Ensure content is above overlay */
    top: 50%;
    left: 30%;
    transform: translate(-50%, -50%); /* Center content */
    color: white; /* Text color */
    font-weight: 900;
    font-size: 60px;
}


.new {
    padding: 2rem 0;
}

.routine__container {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.routine__content {
    display: flex;
    flex-direction: row;
    align-items: center;
}

.routine__image {
    flex: 1;
    padding-right: 2rem;
}

.routine__image img {
    width: 100%;
    height: auto;
    object-fit: cover;
}

.routine__info {
    flex: 1;
    padding-left: 2rem;
}

.routine__title {
    font-size: 2rem;
    font-weight: 700;
    margin-bottom: 1rem;
}

.routine__description {
    font-size: 1rem;
    color: #666;
    margin-bottom: 1.5rem;
}


@media (max-width: 768px) {
    .routine__content {
        flex-direction: column;
    }

    .routine__image,
    .routine__info {
        padding: 0;
    }
}

.social__description {
    text-align: center;
}


.social__title {
    font-size: 2rem;
    font-weight: 700;
    text-align: center;
    margin-bottom: 2rem;
}

.social__container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 1rem;
    margin-bottom: 2rem;
}

.social__card {
    display: flex;
    align-items: center;
    justify-content: center;
    transition: transform 0.3s ease, box-shadow 0.3s ease;

}

.social__card:hover {
    transform: scale(1.05);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}

.social__img {
    width: 100%;
    height: auto;
    object-fit: cover;
}

.footer {
    background-color: #f8f8f8;
    padding: 2rem 0;
}

.footer__content {
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-wrap: wrap;
}

.footer__left {
    flex: 1;
}

.footer__title {
    font-size: 1.5rem;
    font-weight: 700;
    margin-bottom: 0.5rem;
}

.footer__left p {
    margin: 0;
}

.footer__left a {
    color: #6C63FF;
    text-decoration: none;
}

.footer__left a:hover {
    text-decoration: underline;
}

.footer__right {
    display: flex;
    gap: 2rem;
    flex: 1;
    justify-content: flex-end;
}

.footer__info {
    flex: 1;
}

.footer__info-title {
    font-weight: 600;
    margin-bottom: 0.5rem;
}

.footer__info p {
    margin: 0;
}

@media (max-width: 768px) {
    .footer__content {
        flex-direction: column;
        align-items: flex-start;
    }

    .footer__right {
        flex-direction: column;
        align-items: flex-start;
        gap: 1rem;
        margin-top: 1rem;
    }

    .footer__info {
        flex: none;
    }
}
.cssbuttons-io {
    position: relative;
    font-family: inherit;
    font-weight: 500;
    font-size: 18px;
    letter-spacing: 0.05em;
    border-radius: 0.8em;
    cursor: pointer;
    border: none;
    background: linear-gradient(to right, #8e2de2, #4a00e0);
    color: ghostwhite;
    overflow: hidden;
  }
  
  .cssbuttons-io svg {
    width: 1.2em;
    height: 1.2em;
    margin-right: 0.5em;
  }
  
  .cssbuttons-io span {
    position: relative;
    z-index: 10;
    transition: color 0.4s;
    display: inline-flex;
    align-items: center;
    padding: 0.8em 1.2em 0.8em 1.05em;
  }
  
  .cssbuttons-io::before,
  .cssbuttons-io::after {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 0;
  }
  
  .cssbuttons-io::before {
    content: "";
    background: #222222;
    width: 120%;
    left: -10%;
    transform: skew(30deg);
    transition: transform 0.4s cubic-bezier(0.3, 1, 0.8, 1);
    box-shadow: inset 0 0 0 1px #19242b;
  }
  
  .cssbuttons-io:hover::before {
    transform: translate3d(100%, 0, 0);
  }
  
  .cssbuttons-io:active {
    transform: scale(0.95);
  }
  

  .signin {
    float: right;
    max-width: 320px;
    display: flex;
    padding: 0.3rem .8rem;
    font-size: 0.875rem;
    line-height: 1.25rem;
    font-weight: 700;
    text-align: center;
    font-family: "Montserrat", sans-serif;
    vertical-align: middle;
    align-items: center;
    border-radius: 0.5rem;
    border: 1px solid #19242b;
    gap: 0.75rem;
    color: #19242b;
    background-color: whitesmoke;
    cursor: pointer;
    transition: all 0.25s cubic-bezier(0, 0.87, 0.12, 1);
    /* box-shadow: inset 0 0 0 1px #19242b; */
}

.signin:hover {
    transform: scale(1.025);
}

.signin:active {
    transform: scale(0.975);
}

.svg__google {
    height: 24px;
    width: auto;
}


.routine__button {
  display: inline-block;
  padding: 0.75rem 1.5rem;
  background-color: #6C63FF;
  color: #fff;
  text-decoration: none;
  border-radius: 0.5rem;
  transition: background-color 0.3s;
}

.routine__button:hover {
  background-color: #5752d4;
}

.youtube__button{
  display: inline-block;
  padding: 10px 12px;
  /* border: 1px solid #4f4f4f; */
  border: none;
  border-radius: 10px;
  transition: all 0.2s ease-in;
  position: relative;
  overflow: hidden;
  font-size: 14px;
  cursor: pointer;
  color: black;
  z-index: 1;
}

.youtube__button:before {
  content: "";
  position: absolute;
  left: 50%;
  transform: translateX(-50%) scaleY(1) scaleX(1.25);
  top: 100%;
  width: 140%;
  height: 180%;
  background-color: rgba(0, 0, 0, 0.05);
  border-radius: 50%;
  display: block;
  transition: all 0.5s 0.1s cubic-bezier(0.55, 0, 0.1, 1);
  z-index: -1;
}

.youtube__button:after {
  content: "";
  position: absolute;
  left: 55%;
  transform: translateX(-50%) scaleY(1) scaleX(1.45);
  top: 180%;
  width: 160%;
  height: 190%;
  background-color: #3297CE;
  border-radius: 50%;
  display: block;
  transition: all 0.5s 0.1s cubic-bezier(0.55, 0, 0.1, 1);
  z-index: -1;
}

.youtube__button:hover {
  color: #ffffff;
  border: 1px solid#3297CE;
}

.youtube__button:hover:before {
  top: -35%;
  background-color: #3297CE;
  transform: translateX(-50%) scaleY(1.3) scaleX(0.8);
}

.youtube__button:hover:after {
  top: -45%;
  background-color: #3297CE;
  transform: translateX(-50%) scaleY(1.3) scaleX(0.8);
}



.social__button-container {
    display: flex;
    /* justify-content: center; */
}

.social__button {
    padding: 0.75rem 1.5rem;
    background-color: #6C63FF;
    color: #fff;
    text-decoration: none;
    border-radius: 0.5rem;
    transition: background-color 0.3s;
}

.social__button:hover {
    background-color: #5752d4;
}


/* Scroll to Top Button */
.scroll-top-button {
  display: block;
  position: fixed;
  bottom: 20px;
  right: 30px;
  width: 56px;
  height: 56px;
  margin: 0;
  overflow: hidden;
  outline: none;
  background-color: transparent;
  cursor: pointer;
  border: 0;
  z-index: 99;
}

.scroll-top-button:before,
.scroll-top-button:after {
  content: "";
  position: absolute;
  border-radius: 50%;
  inset: 7px;
}

.scroll-top-button:before {
  border: 4px solid #ff0c86;
  transition: opacity 0.4s cubic-bezier(0.77, 0, 0.175, 1) 80ms,
    transform 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955) 80ms;
}

.scroll-top-button:after {
  border: 4px solid #96daf0;
  transform: scale(1.3);
  transition: opacity 0.4s cubic-bezier(0.165, 0.84, 0.44, 1),
    transform 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94);
  opacity: 0;
}

.scroll-top-button:hover:before,
.scroll-top-button:focus:before {
  opacity: 0;
  transform: scale(0.7);
  transition: opacity 0.4s cubic-bezier(0.165, 0.84, 0.44, 1),
    transform 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94);
}

.scroll-top-button:hover:after,
.scroll-top-button:focus:after {
  opacity: 1;
  transform: scale(1);
  transition: opacity 0.4s cubic-bezier(0.77, 0, 0.175, 1) 80ms,
    transform 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955) 80ms;
}

.top-button-box {
  display: flex;
  position: absolute;
  top: 0;
  left: 0;
}

.top-button-elem {
  display: block;
  width: 20px;
  height: 20px;
  margin: 17px 18px 0 18px;
  fill: #ff0c86;
}

.scroll-top-button:hover .top-button-box,
.scroll-top-button:focus .top-button-box {
  transition: 0.4s;
  transform: translateY(-56px);
}

  </style>
  