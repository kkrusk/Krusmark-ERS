function datetime(id) {
    var date = new Date;
    var year = date.getFullYear();
    var month = date.getMonth();
    var months = new Array('January', 'February', 'March', 'April', 'May', 'June', 'Jully', 'August', 'September', 'October', 'November', 'December');
    var d = date.getDate();
    var day = date.getDay();
    var days = new Array('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday');
    var h = date.getHours();
    
    if(h<10) {
        h = "0"+h;
    }
    
    var m = date.getMinutes();
    
    if(m<10) {
        m = "0"+m;
    }
    
    var s = date.getSeconds();
        
    if(s<10) {
        s = "0"+s;
    }
    
    var result = ''+days[day]+', '+months[month]+' '+d+', '+year+' '+h+':'+m+':'+s;
    document.getElementById(id).innerHTML = result;
    
    setTimeout('datetime("'+id+'");','1000');
    
    return true;
}