window.addEventListener("load", start);

var usernameSet = document.getElementsByClassName("userName");

function start()
{
    displayUserAccount();

    let modal = document.getElementById('changeImgBlock');
    let changeButton = document.getElementById("changing");
    let usernameField = document.getElementById("username");
    let passwordCheckField = document.getElementById("passwordCheck");
    let useFile = document.getElementById("useFile");
    let notUseFile = document.getElementById("notUseFile");
    let fileDiv = document.getElementById("checkFile");
    let fileValue = document.getElementById("myFile");
    let infoEnsure = document.getElementById("infoEnsure");
    let storeAvatar = document.getElementById("storeAvatar");

    displayAvatar();

    changeButton.addEventListener("click", changingImg);
    usernameField.addEventListener("focusout", testUsername);
    passwordCheckField.addEventListener("focusout", testPassword);

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event)
    {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    useFile.addEventListener("click", function () {
        if (useFile.checked)
            fileDiv.style.visibility = "initial";
    });

    notUseFile.addEventListener("click", function () {
        if (notUseFile.checked)
        {
            fileDiv.style.visibility = "hidden";
            avatarImg.src = "./images/image-avatar-1.png";
            document.cookie = "avatarCookie=" + JSON.stringify("image-avatar-1.png");
        }
    });

    infoEnsure.addEventListener("click", function ()
    {
        let cookieValue = getCookie("avatarCookie");

        if (infoEnsure.checked)
        {
            if (cookieValue.length == 0)
                cookieValue = "image-avatar-1.png";
            else
                cookieValue = JSON.parse(cookieValue);

            if (((fileValue.value).length == 0) || (notUseFile.checked))
            {
                storeAvatar.value = cookieValue;
                fileDiv.remove();
            }
            else
                storeAvatar.value = "none";
        }
    });
}

function displayUserAccount()
{
    for (let i = 0; i < usernameSet.length; i++)
    {
        console.log("account " + (i+1) + ": " + usernameSet[i].innerHTML);
    }
}

function displayAvatar()
{
    let cookieAvatar = getCookie("avatarCookie");
    let imgAvatar = document.getElementById("avatarImg");

    if (cookieAvatar.length == 0)
        imgAvatar.src = "./images/image-avatar-1.png";
    else
    {
        let imageName = JSON.parse(cookieAvatar);
        imgAvatar.src = "./images/" + imageName;
    }
}

function popUpAvatar()
{
    // Get the modal
    let modal = document.getElementById('changeImgBlock');
    modal.style.display = "block";
}

function changingImg()
{
    let imageName = getImageName();

    if (imageName == "none")
        imageName = "image-avatar-1.png";

    document.cookie = "avatarCookie=" + JSON.stringify(imageName);

    displayAvatar();
}

function getImageName()
{
    for (let i = 1; i <= 4; i++)
    {
        let currentImg = document.getElementById("avatar" + i);
        if (currentImg.checked)
            return currentImg.value;
    }
    return "none";
}

function testUsername()
{
    let usernameValue = document.getElementById("username").value;
    let currentUsername = document.getElementById("currentUsername");

    for (let i = 0; i < usernameSet.length; i++)
    {
        if ((usernameValue != currentUsername.innerHTML) && (usernameValue == usernameSet[i].innerHTML))
        {
            alert("This username already be taken!");
            disableButton();
            break;
        }
        else
            enableButton();
    }
}

function testPassword()
{
    let firstPassword = document.getElementById("password").value;
    let secondPassword = document.getElementById("passwordCheck").value;

    if (firstPassword != secondPassword)
    {
        alert("The password are not the same!");
        disableButton();
    }
    else
        enableButton();
}

function disableButton()
{
    let createButton = document.getElementById("createButton");
    createButton.disabled = true;
    createButton.style.backgroundColor = "gray";
    createButton.style.cursor = "initial";
}

function enableButton()
{
    let createButton = document.getElementById("createButton");
    createButton.disabled = false;
    createButton.style.backgroundColor = "0059ab";
    createButton.style.cursor = "pointer";
}

function getCookie(cname)
{
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++)
    {
        var c = ca[i];
        while (c.charAt(0) == ' ')
        {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0)
        {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}