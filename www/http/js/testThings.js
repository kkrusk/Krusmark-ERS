var someNumber = 0;

function setStopper() {
    someNumber = $('#stopper').offset().top - $('#stopper').height();
};

$(document).ready(function() {
    $(window).scroll(function () {
        //if you hard code, then use console
        //.log to determine when you want the 
        //nav bar to stick.  
        console.log($(window).scrollTop());
        
        if ($(window).scrollTop() > someNumber) {
            $('#stopper').addClass('navbar-fixed');
        }
        if ($(window).scrollTop() < someNumber + 1) {
            $('#stopper').removeClass('navbar-fixed');
        }
    });
});