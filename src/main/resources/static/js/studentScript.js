function renderMark(el) {
    return "        <div style='display: flex;'>\n" +
        "            <h5>Mark: "+ el.mark +"</h5>\n" +
        "            <h5 style='margin-left: 20px'>Description: "+ el.discription +"</h5>\n" +
        "        </div>"
}

async function setFaculties() {
    let jwt = localStorage.getItem("jwt");
    let userName = document.getElementById("username").textContent;
    let response = await fetch(`/api/v1/students/getUserSubjects?username=${userName}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt,'Content-Type': 'application/json', 'Accept': 'application/json'}
        });
    let data = await response.json();
    console.log(data);
    var select = document.getElementById("subjects");

    var i = 1;
    select.innerHTML = "";
    data.forEach(el => {
        select.innerHTML += "<option value=\"Choice " + i + "\">" + el + "</option>";
        i++;
    });
}

load = async () => {
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if (jwt == null) {
        document.location.href = "/login";
    }
    let user_info = await fetch('api/v1/userinfo/',
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    console.log(user_info_json);
    let length = Object.keys(user_info_json).length;
    document.getElementById("username").innerHTML = user_info_json.username;
    setFaculties();
};
let page = 0;
getMarks = async () => {
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if (jwt == null) {
        document.location.href = "/login";
    }
    let username = document.getElementById("username").textContent;
    let sel2 = document.getElementById("subjects");
    let subject = sel2.options[sel2.selectedIndex].textContent;
    let user_info = await fetch(`/api/v1/students/getUserMarks?username=${username}&subject=${subject}&page=${page}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    page = 0;
    let user_info_json = await user_info.json();

    let content = document.getElementById("content");
    content.innerHTML = "";
    user_info_json.content.forEach(el => {
        content.innerHTML += renderMark(el);
    });

    checkDisabled(user_info_json);
};

prev = async () =>{
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if (jwt == null) {
        document.location.href = "/login";
    }
    let username = document.getElementById("username").textContent;
    let sel2 = document.getElementById("subjects");
    let subject = sel2.options[sel2.selectedIndex].textContent;
    if(page > 0) {
        page--;
    }
    console.log(page);
    let user_info = await fetch(`/api/v1/students/getUserMarks?username=${username}&subject=${subject}&page=${page}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    console.log(user_info_json);
    let content = document.getElementById("content");
    content.innerHTML = "";
    user_info_json.content.forEach(el => {
        content.innerHTML += renderMark(el);
    });
    checkDisabled(user_info_json);
};

next = async () =>{
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if (jwt == null) {
        document.location.href = "/login";
    }
    let username = document.getElementById("username").textContent;
    let sel2 = document.getElementById("subjects");
    let subject = sel2.options[sel2.selectedIndex].textContent;
    page++;
    let user_info = await fetch(`/api/v1/students/getUserMarks?username=${username}&subject=${subject}&page=${page}`,
        {
            method: 'GET',
            headers: {'Authorization': 'Bearer_' + jwt, 'Accept': 'application/json'}
        });
    let user_info_json = await user_info.json();
    console.log(user_info_json);
    let content = document.getElementById("content");
    content.innerHTML = "";
    user_info_json.content.forEach(el => {
        content.innerHTML += renderMark(el);
    });
    checkDisabled(user_info_json);
};

checkDisabled = (user_info_json) =>  {
    document.getElementById("previous").classList.disabled = true;
    document.getElementById("next").classList.disabled = true;
    if(page == 0) {
        document.getElementById("previous").disabled = true;
    }else {
        document.getElementById("previous").disabled = false;
    }

    if(page == user_info_json.totalPages - 1){
        document.getElementById("next").disabled = true;
    }else{
        document.getElementById("next").disabled = false;
    }
};

logout = () => {
    localStorage.clear();
    document.location.href = "/login";
}

load();