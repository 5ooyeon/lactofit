<template>
    <div>
        <p>SSAFIT 가입하기</p>
        <div class="custom-floating">
            <input type="email" name="userEmail" placeholder=" " class="floating-input">   
            <label for="userEmail" class="floating-label">이메일</label>
        </div>
        <div class="custom-floating">
            <input type="password" name="userPw" class="floating-input" v-model="password" rules="required|min:8">   
            <label for="userPw" class="floating-label">비밀번호</label>
        </div>
        <div class="custom-floating">
            <input type="password" name="passwordCheck" placeholder=" " class="floating-input" rules="{required:true, is: password }">   
            <label for="passwordCheck" class="floating-label">비밀번호확인</label>
        </div>
        <div class="custom-floating">
            <input type="text" name="userNickname" placeholder=" " class="floating-input">   
            <label for="userNickname" class="floating-label">닉네임</label>
        </div>
        <div class="custom-floating">
            <input type="radio" name="userGender" value="M" id="M" class="radio-input">
            <label for="M" class="radio-label">남성</label>
            <input type="radio" name="userGender" value="F" id="F" class="radio-input">
            <label for="F" class="radio-label">여성</label>
            <span class="floating-label">성별</span>
        </div>
        <div class="custom-floating">
            <input type="date" class="floating-input">
            <label for="userBirthdate" class="floating-label">생일</label>
        </div>
        <div>
            <button>등록하기</button>
        </div>
    </div>
</template>

<script setup>
</script>

<style scoped>

</style>