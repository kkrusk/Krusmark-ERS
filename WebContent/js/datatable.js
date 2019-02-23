function readJSON(id,role) {
// $("#dataTableBuffer").html("");
	console.log("about to read JSON. Role: " + role);
	
	$.getJSON("jackson?u="+id, function(data){
		drawTable(data, role);
		collapsable(jQuery);
	});
	
// setTimeout('readJSON();','5000');
}

function drawTable(data, role) {
	console.log("read!!");

    for (var i = 0; i < data.length; i++) {
        drawRow(data[i]);
        drawDescription(data[i], role);
    }
}

function drawRow(rowData) {	
    var row = $("<tr />")
    $("#dataTableBuffer").append(row); // this will append tr element to
										// table... keep its reference for a
										// while since we will add cels into it
    row.append($("<td>" + "$" + parseFloat(Math.round(rowData.amount * 100) / 100).toFixed(2) + "</td>"));
    row.append($("<td>" + returnDate(rowData.submitted) + "</td>"));
    row.append($("<td>" + returnDate(rowData.resolved) + "</td>"));
    row.append($("<td>" + rowData.author + "</td>"));
    row.append($("<td>" + (rowData.resolver!=null?rowData.resolver:"-") + "</td>"));
    row.append($("<td>" + rowData.type + "</td>"));
    row.append($("<td class='color_"+rowData.status+"'><strong>" + rowData.status + "</strong></td>"));

}

function drawDescription(rowData, role) {
    var row = $("<tr class='descriptx' />")
    $("#dataTableBuffer").append(row);
    row.append($("<td colspan='8'>"
			+ "<dl class='row'>"
			+ "<dt class='col-sm-2'>Description</dt>"
			+ "<dd class='col-sm-10'>" + rowData.description + "</dd>"
			+ "</dl><hr><dl class='row'>"
			+ "<dt class='col-sm-2'>Actions</dt>"
			+ "<dd class='col-sm-10'>" // start of buttons
			+ ((role==1)?drawBtns(rowData.id, rowData.status, rowData.description):drawReceiptBtn(rowData.id,rowData.description))
			+ "</dd>"
			+ "</td>"));
	
}

function returnDate(data) {
	if (data != null) {
		var date = new Date(data);
		return date.getMonth()+1 + '/' + date.getDate() + '/' + date.getFullYear();
	}
	return "-";
}

function drawReceiptBtn(rid, d) {
	return "<button type='button' onclick='encode(" + rid + ");loadDescription(\"" +  d + "\")' class='btn btn-info mr-2' data-toggle='modal' data-target='#myModal'>Receipt</button>";
}

function drawBtns(rid, status, d) {
	var acceptBtn = "<button type='submit' name='status' value='2' class='btn btn-success mr-2'>Success</button>";
	var denyBtn = "<button type='submit' name='status' value='3' class='btn btn-danger mr-2'>Deny</button>";
	
	var out = "<form action='update' method='post'>"
		+ "<input type='hidden' name='reimb_id' value='" + rid + "'>"
		+ ((status=="Pending")?acceptBtn+denyBtn+drawReceiptBtn(rid,d):drawReceiptBtn(rid,d))
		+ "</form>";
	
	return out;
}

function collapsable($) {
    $(function () {
        $('.table-expandable').each(function () {
            var table = $(this);
            table.children('thead').children('tr').filter(':even').append("<th><button class=\"btn btn-dark\" onclick=\"exportTableToCSV(\'members.csv\')\">&#187;</button></th>");
            table.children('thead').children('tr').filter(':odd').append("<th></th>");
            table.children('tbody').children('tr').filter(':odd').hide();
            table.children('tbody').children('tr').filter(':even').click(function () {
                var element = $(this);
                element.next('tr').toggle('slow');
                element.find(".table-expandable-arrow").toggleClass("up");
            });
            table.children('tbody').children('tr').filter(':even').each(function () {
                var element = $(this);
                element.append('<td><div class="table-expandable-arrow m-0"></div></td>');
            });
        });
    });
} 


function encode(code) {
	$.ajax({ url: 'crypt?s='+code , success: function(data){
		loadEncodedImage(data);
	} });
}

function loadEncodedImage(code) {
	document.getElementById("hiddenReceipt").innerHTML = "<img src='image?r=" + code + "' width='100%'>";
}

function loadDescription(code) {
	document.getElementById("hiddenDesc").innerHTML = "<p class='p-3'>" + code + "</p>";
}

function sortTable(n) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById("datatable");
	switching = true;
	
	// Set the sorting direction to ascending:
	dir = "asc";
	
	// Make a loop that will continue until no switching has been done:
	
	while (switching) {
		// start by saying: no switching is done:
		switching = false;
		rows = table.getElementsByTagName("TR");
	    
		// Loop through all table rows (except the first, which contains table
	    for (i = 1; i < (rows.length - 1); i++) {
	    	// start by saying there should be no switching:
	    	shouldSwitch = false;

	    	/*
			 * Get the two elements you want to compare, one from current row
			 * and one from the next:
			 */
	      
	    	x = rows[i].getElementsByTagName("TD")[n];
	    	y = rows[i + 1].getElementsByTagName("TD")[n];
	      
	    	/*
			 * check if the two rows should switch place, based on the
			 * direction, asc or desc:
			 */
	      
	    	if (dir == "asc") {
	    		if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
	    			// if so, mark as a switch and break the loop:
	    			shouldSwitch= true;
	    			break;
	    		}
	    	} else if (dir == "desc") {
	    		if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
	    			// if so, mark as a switch and break the loop:
	    			shouldSwitch= true;
	    			break;
	    		}
	    	}
	    }
	    
	    if (shouldSwitch) {
	    	
	    	/*
			 * If a switch has been marked, make the switch and mark that a
			 * switch has been done:
			 */

	    	rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
	    	switching = true;
	    	
	    	// Each time a switch is done, increase this count by 1:
	    	switchcount ++;      
	    } else {
	
	    	/*
			 * If no switching has been done AND the direction is "asc", set the
			 * direction to "desc" and run the while loop again.
			 */
	    	
	    	if (switchcount == 0 && dir == "asc") {
	    		dir = "desc";
	    		switching = true;
	    	}
	    }
	}
}

function filterTable(col, id) {
	var input, filter, table, tr, td, i;
	
	input = document.getElementById(id);	
	filter = input.value.toUpperCase();
	table = document.getElementById("datatable");
	tr = table.getElementsByTagName("tr");

	if (filter == "-")
		filter = "";
	
	//table.children('tbody').children('tr').filter(':odd').hide();
	
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[col];
	    if (td) {
	    	if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	    		tr[i].style.display = "";
	    	} else {
	    		tr[i].style.display = "none";
	    	}
	    }
	}
}

function fltrTbl() {
	var f1 = document.getElementById("datefilter").value.toUpperCase();
	var f2 = document.getElementById("filterTableInput").value.toUpperCase();
	var f3 = document.getElementById("type").value.toUpperCase();
	var table = document.getElementById("datatable");
	var tr = table.getElementsByTagName("tr");
	var td, i;
	
	if (f3 == "-") { f3 = ""; }
	
	var date1, date2;
	
	// Parse date
	if (f1.indexOf('-') != -1) {
		date1 = new Date(f1.substring(0,f1.indexOf('-')-1));
		date1.setDate(date1.getDate()-1);
		date2 = new Date(f1.substring(f1.indexOf('-')+2,f1.length));
		date2.setDate(date2.getDate()+1);
		//console.log("[" + date1 + "][" + date2 + "]");
	} else {
		date1 = new Date(0);
		date2 = new Date();
	}
		
	
	$('.table-expandable').each(function () {
        var table = $(this);
        table.children('tbody').children('tr').filter(':odd').hide();
	});
	//table.children('tbody').children('tr').filter(':odd').hide();
	
	var tdDate, tdAuthor, tdType, dateVal;
	
	for (i = 0; i < tr.length; i++) {
		tdDate = tr[i].getElementsByTagName("td")[1];
		tdAuthor = tr[i].getElementsByTagName("td")[3];
		tdType = tr[i].getElementsByTagName("td")[6];
		
		
	    if (tdDate && tdAuthor && tdType) {
			dateVal = new Date(tr[i].getElementsByTagName("td")[1].innerHTML.toUpperCase())
		
			if (
	    			((dateVal > date1) && (dateVal < date2))
	    			&& (tr[i].getElementsByTagName("td")[3].innerHTML.toUpperCase().indexOf(f2) > -1)
	    			&& (tr[i].getElementsByTagName("td")[6].innerHTML.toUpperCase().indexOf(f3) > -1)
	    		) {
	    		tr[i].style.display = "";
	    	} else {
	    		tr[i].style.display = "none";
	    	}
	    }
	}
}

function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;

    // CSV file
    csvFile = new Blob([csv], {type: "text/csv"});

    // Download link
    downloadLink = document.createElement("a");

    // File name
    downloadLink.download = filename;

    // Create a link to the file
    downloadLink.href = window.URL.createObjectURL(csvFile);

    // Hide download link
    downloadLink.style.display = "none";

    // Add the link to DOM
    document.body.appendChild(downloadLink);

    // Click download link
    downloadLink.click();
}

function exportTableToCSV(filename) {
	console.log("here");
    var csv = [];
    var rows = document.getElementById("tableToExport").querySelectorAll("table tr");
    
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        
        for (var j = 0; j < cols.length; j++) 
            row.push(cols[j].innerText);
        
        csv.push(row.join(","));        
    }

    // Download CSV file
    downloadCSV(csv.join("\n"), filename);
}