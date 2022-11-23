const weather = {
    status:"rainy",
    temp:"23 Degrees",
    message:"This is a loop in javascript"
}

let loop = document.getElementById("loop");


let ul = document.createElement("ul");

for(const x in weather){
    let li = document.createElement("li");
    li.appendChild(document.createTextNode(weather[x]));
    ul.appendChild(li);
}





loop.appendChild(ul);
