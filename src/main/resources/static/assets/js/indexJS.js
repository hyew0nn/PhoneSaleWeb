function initUI() {
    let userId = sessionStorage.getItem("id");
    let userName = sessionStorage.getItem("name");

    if (userId == null) {
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
    let url = "api/auth/logout";

    let response = await fetch(url);
    let data = await response.json()

    console.log(data);

    if(data.result == "success"){
        sessionStorage.removeItem("id");
        sessionStorage.removeItem("name");

        // 한꺼번에 전체 삭제
        // sessionStorage.clear();

        // 로그인 활성화
        initUI()
    }
}