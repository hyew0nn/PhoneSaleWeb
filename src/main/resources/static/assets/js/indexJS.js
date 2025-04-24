function initUI() {
    let userName = sessionStorage.getItem("email");

    if (userName == null) {
        document.querySelector(".user-name").innerHTML = '';
        document.querySelector(".user-name-wrapper").style.display = "none";
        document.querySelector(".join-btn").style.display = "inline";
        document.querySelector(".login-btn").style.display = "inline";

        document.querySelector(".logout-btn").style.display = "none";
    } else {
        document.querySelector(".user-name").innerHTML = userName;
        document.querySelector(".user-name-wrapper").style.display = "inline";
        document.querySelector(".join-btn").style.display = "none";
        document.querySelector(".login-btn").style.display = "none";

        document.querySelector(".logout-btn").style.display = "inline";
    }
}

async function logout(){
    // url
    let url = "/users/logout";

    let response = await fetch(url);
    let data = await response.json()

    console.log(data);

    if(data.result == "success"){
        // user 의 name, email 을 삭제 ( 개별 처리 )
        sessionStorage.removeItem("name");
        sessionStorage.removeItem("email");

        // 한꺼번에 전체 삭제
        // sessionStorage.clear();

        // 로그인 활성화
        initUI()
    }
}