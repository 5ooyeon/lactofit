<template>
  <header class="navbar navbar-expand-lg rounded">
        <nav class="container">
          <div class="navbar-collapse d-lg-flex" id="navbarsExample11">
            <a class="navbar-brand col-lg-3 me-0" href="#">SSAFIT</a>
            <ul class="navbar-nav col-lg-6 justify-content-lg-center">
              <li class="nav-item">
                <a class="nav-link" href="#routine">운동백과</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#youtube">영상PICK</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#social">#오운완</a>
              </li>
            </ul>
            <div class="d-lg-flex col-lg-3 justify-content-lg-end">
              <button class="signin">
                <svg
                  class="svg__google"
                  viewBox="0 0 256 262"
                  preserveAspectRatio="xMidYMid"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    d="M255.878 133.451c0-10.734-.871-18.567-2.756-26.69H130.55v48.448h71.947c-1.45 12.04-9.283 30.172-26.69 42.356l-.244 1.622 38.755 30.023 2.685.268c24.659-22.774 38.875-56.282 38.875-96.027"
                    fill="#4285F4"
                  ></path>
                  <path
                    d="M130.55 261.1c35.248 0 64.839-11.605 86.453-31.622l-41.196-31.913c-11.024 7.688-25.82 13.055-45.257 13.055-34.523 0-63.824-22.773-74.269-54.25l-1.531.13-40.298 31.187-.527 1.465C35.393 231.798 79.49 261.1 130.55 261.1"
                    fill="#34A853"
                  ></path>
                  <path
                    d="M56.281 156.37c-2.756-8.123-4.351-16.827-4.351-25.82 0-8.994 1.595-17.697 4.206-25.82l-.073-1.73L15.26 71.312l-1.335.635C5.077 89.644 0 109.517 0 130.55s5.077 40.905 13.925 58.602l42.356-32.782"
                    fill="#FBBC05"
                  ></path>
                  <path
                    d="M130.55 50.479c24.514 0 41.05 10.589 50.479 19.438l36.844-35.974C195.245 12.91 165.798 0 130.55 0 79.49 0 35.393 29.301 13.925 71.947l42.211 32.783c10.59-31.477 39.891-54.251 74.414-54.251"
                    fill="#EB4335"
                  ></path>
                </svg>
                Google 로그인
              </button>
            </div>
          </div>
        </nav>
      </header>
</template>

<script setup>

</script>



<style scoped>
.navbar {
    position:fixed;
    top: 0;
    left: 0;
    width: 100%;
    background-color: white;
    z-index: var(--z-fixed);
    transition: .4s;
}

.navbar-brand, .nav-link {
    color: #333; /* 네비게이션 링크 색상 변경 */
}

.navbar-brand:hover, .nav-link:hover {
    color: #000; /* 호버 상태에서 색상 강조 */
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


</style>
