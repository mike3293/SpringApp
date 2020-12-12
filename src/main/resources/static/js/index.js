load = async () => {
    let jwt = localStorage.getItem("jwt");
    console.log(jwt);
    if(jwt== null){
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

    let details = document.getElementById("list");

    details.innerHTML += "<div>Username: </div>";
    details.innerHTML += "<div>" + user_info_json.username + "</div>";

    console.log(user_info_json);
    //admin
    if(length == 8)
    {
        details.innerHTML += "<div>First Name: </div>";
        details.innerHTML += "<div>" + user_info_json.firstName + "</div>";
        details.innerHTML += "<div>Surname: </div>";
        details.innerHTML += "<div>" + user_info_json.lastName + "</div>";
        details.innerHTML += "<div>Email: </div>";
        details.innerHTML += "<div>" + user_info_json.email + "</div>";
        details.innerHTML += "<div>Middle Name: </div>";
        details.innerHTML += "<div>" + user_info_json.middleName + "</div>";
        details.innerHTML += "<div>Faculty: </div>";
        details.innerHTML += "<div>" + user_info_json.facultyName.faculty + "</div>";
        details.innerHTML += "<div>Course: </div>";
        details.innerHTML += "<div>" + user_info_json.userCourse + "</div>";
        details.innerHTML += "<div>Group: </div>";
        details.innerHTML += "<div>" + user_info_json.userGroup + "</div>";

        let ulk2 = document.getElementById("buttonRedirect");
        ulk2.innerHTML += "<button type=\"button\" onclick='onStudentPage()' style='margin-top: 10px'>view ratings</button>";
    }else if(length == 4){
        details.innerHTML += "<div>First Name: </div>";
        details.innerHTML += "<div>" + user_info_json.firstName + "</div>";
        details.innerHTML += "<div>Surname: </div>";
        details.innerHTML += "<div>" + user_info_json.lastName + "</div>";
        details.innerHTML += "<div>Middle Name: </div>";
        details.innerHTML += "<div>" + user_info_json.middleName + "</div>";

        let ulk2 = document.getElementById("buttonRedirect");
        ulk2.innerHTML += "<button type=\"button\" onclick='onTeacherPage()' style='margin-top: 10px'>rate students</button>";
        document.getElementById("list").style.marginTop = "30px";
    }else
    {
        let button = document.getElementById("buttonRedirect");
        button.innerHTML += "<button type=\"button\" onclick='onAdminPage()' style='margin-top: 10px'>admin panel</button>";
        document.getElementById("list").style.marginTop = "50px";
        document.getElementById("but").innerHTML = "";
    }

};

onAdminPage = () => {
    document.location.href = "/register";
};

onStudentPage = () => {
    document.location.href = "/student";
};

onTeacherPage = () => {
    document.location.href = "/teacher";
};

logout = () => {
    localStorage.clear();
    document.location.href = "/login";
};
load();