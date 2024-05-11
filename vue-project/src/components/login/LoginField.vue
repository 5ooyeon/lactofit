<template>
    <div>
        <div class="info">
            <span>SSAFIT</span>
            <p>로그인 후 이용하실 수 있습니다.</p>
        </div>
        <div class="loginField">
            <div>
                <input type="email" name="userEmail" v-model="email" placeholder="이메일">   
            </div>
            <div>
                <input type="password" name="userpw" v-model="password" placeholder="비밀번호">
            </div>
            <button>로그인</button>
        </div>
        <div class="signUp">
            <span style="font-weight: 300; color: darkgray;">SSAFIT이 처음이라면?</span><RouterLink to="/">회원가입 하러가기</RouterLink>
        </div>
        <div class="socialLogin">
            <p>또는 SNS로 로그인하기</p>
            <button class="kakao">카카오로 로그인</button>
        </div>
        <div class="findMyPw">
            <span style="font-weight: 300; color: darkgray;">비밀번호를 잊어버리셨나요?</span><RouterLink to="/">비밀번호 찾기</RouterLink><br>
        </div>
    </div>

        <!-- <p class="info"></p>
    
        <div>
            <legend>로그인 입력 폼</legend>
            <p class="id_field">
            <label for="uid" id="uid_label" style="display: block;"><span class="zh" data-translate="아이디">아이디</span></label>
            <input id="uid" name="uid" type="text" title="아이디입력" maxlength="50" tabindex="1">
            </p>
            <p class="pw_field">
                <label for="password" id="pass_label"><span class="zh" data-translate="비밀번호">비밀번호</span></label>
                <input id="password" name="password" type="password" title="비밀번호입력" maxlength="15" tabindex="2">
                <span class="caps_Lock" style="display:none;">
                    <em></em>
                    <strong>Caps Lock</strong>이 켜져 있습니다.
                </span>
            </p>
            <p class="login_btn">
                <button type="button" onclick="login();" tabindex="3"><span class="zh" data-translate="로그인">로그인</span></button>
            </p>
  
            <div class="account_area">

                <a href="https://member.afreecatv.com/app/find_id_pw.php" class="find_id">아이디 찾기</a><span class="bar">|</span><a href="https://member.afreecatv.com/app/find_id_pw.php?szWork=FIND_PWD" class="account_find">비밀번호 찾기</a><span class="bar">|</span><a href="https://member.afreecatv.com/app/join.php">회원가입</a>

            </div>
            <div class="signup_area">
                
                
                <a href="" id="btnConnectKakao" class="kakao"><em></em></a>
                
            </div>

        </div> -->


</template>

<style scoped>
.info span {
    font-size: 30px;
}
</style>