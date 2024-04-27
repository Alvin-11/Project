//<!--Alvin Biju  Id: 40278182-->
function updateTime() {
    // Create a new Date object
    let now = new Date();
    
    // Get the current time components
    let hours = now.getHours();
    let minutes = now.getMinutes();
    let seconds = now.getSeconds();
    
    // Pad single digit minutes and seconds with leading zeros
    minutes = minutes < 10 ? '0' + minutes : minutes;
    seconds = seconds < 10 ? '0' + seconds : seconds;
    
    // Display the time in the format HH:MM:SS
    let timeString = hours + ':' + minutes + ':' + seconds;

    let currentDate = new Date();

    // Format date as required (e.g., "March 28, 2024")
    let options = { month: 'long', day: 'numeric', year: 'numeric' };
    let formattedDate = currentDate.toLocaleDateString('en-US', options);
    
    // Update the HTML element with id 'current-time' with the new time
    document.getElementById('current-time').innerText = formattedDate +" Current time: " + timeString;
}

function validateForm(){
    let dogbreed = document.getElementById("dogbreed1").value;
    let catbreed = document.getElementById("catbreed1").value;
    let mixbreed = document.getElementById("checkbox3").checked;
   
    let dog = document.getElementById("checkbox1").checked;
    let cat = document.getElementById("checkbox2").checked;
    
    let male = document.getElementById("checkbox4").checked;
    let female = document.getElementById("checkbox5").checked;
   
    
   
    let comment = document.getElementById("comment").value;
    
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("familyname").value;
    let email = document.getElementById("email").value;
    
    let animalage = document.getElementById("animalage");
    let agecategory = document.getElementById("agecategory");

    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; 

    if (!emailRegex.test(email)) {
        window.alert("Please fill out an accepted format for the email");
        return false; 
      }

    if (dogbreed === "" && catbreed === "" && !mixbreed ) {
        window.alert("Please fill out the animal breed field");
        return false;
      }
    
    if (!cat && !dog ) {
        window.alert("Please fill out  the type of animal field");
        return false;
      }
    
    if (!male && !female ) {
        window.alert("Please fill out the gender of the animal field");
        return false;
      }
    
    if (comment === "" ) {
        window.alert("Please fill out the comment field");
        return false;
      }
    if (firstname === "" ) {
        window.alert("Please fill out the firstname field");
        return false;
      }
    if (lastname === "" ) {
        window.alert("Please fill out the lastname field");
        return false;
      }
    if (email === "" ) {
        window.alert("Please fill out the email field");
        return false;
      }

    if (animalage.selectedIndex === 0){
        window.alert("Please fill out the age of animal field");
        return false;
    }
    if (agecategory.selectedIndex === 0){
        window.alert("Please fill out the age category field");
        return false;
    }
    
    return true;
   

}

function validateForm1(){
    let dogbreed = document.getElementById("dogbreed").value;
    let catbreed = document.getElementById("catbreed").value;
    let nobreedmatter = document.getElementById("checkbox8").checked;
   
    let dog = document.getElementById("dogcheckbox").checked;
    let cat = document.getElementById("catcheckbox").checked;

    
    let animalage = document.getElementById("animalagecategory");
    let gender = document.getElementById("preferredgender");
    let suitableforfamily = document.getElementById("suitableforfamily");
   
    if (animalage.selectedIndex === 0){
        window.alert("Please fill out the age of animal field");
        return false;
    }
    if (gender.selectedIndex === 0){
        window.alert("Please fill out the gender of animal field");
        return false;
    }
    if (suitableforfamily.selectedIndex === 0){
        window.alert("Please fill out the suitable for family field");
        return false;
    }
    if (!nobreedmatter && dogbreed==="" && catbreed==="") {
        window.alert("Please fill out the breed field");
        return false;
    }
    if (!dog && !cat) {
        window.alert("Please fill out the type of animal field");
        return false;
    }
    return true;
}
// Call updateTime function every second
setInterval(updateTime, 1000);