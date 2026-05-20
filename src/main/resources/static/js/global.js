document.addEventListener("DOMContentLoaded", function(){
	 
	const msg = document.getElementById("flash-message");
    if (msg) {
        setTimeout(() => {
            msg.classList.add("flash-hide");
        }, 3000); // visible for 3 seconds

        setTimeout(() => {
            msg.remove();
        }, 3800);
    } 
 }); 
