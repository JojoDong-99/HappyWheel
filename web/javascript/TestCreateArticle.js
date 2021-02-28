window.addEventListener("load", start);

function start()
{
    document.getElementById("newTitle").addEventListener("input", showLastEditTime);
    document.getElementById("newContent").addEventListener("input", showLastEditTime);
}

function showLastEditTime()
{
    let currentDate = new Date();
    let format = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };

    let date = currentDate.toLocaleDateString("en-Nz", format);
    let hour = currentDate.getHours();
    let minute = currentDate.getMinutes();
    let second = currentDate.getSeconds();

    hour = hour.toString();
    minute = minute.toString();
    second = second.toString();

    let displayText = hour.padStart(2, "0") + " : " + minute.padStart(2, "0") + " : " + second.padStart(2, "0") + ",  " + date;

    document.getElementById("showLastEditTime").innerText = "last edit at " + displayText;
}