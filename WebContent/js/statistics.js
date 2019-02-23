var arrNames, arrApproved, arrPending, arrDenied;

function getChartData() {
	$.getJSON("report", function(data){
		genCharts(data);
	});
}

function genCharts(json) {   
    var i;
    
    arrNames = new Array(json.length);
    arrPending = new Array(json.length);
    arrApproved = new Array(json.length);
    arrDenied = new Array(json.length);
    
    for (i = 0; i < json.length; i++) {
    	arrNames[i] = json[i].name;
    	arrPending[i] = json[i].pending;
    	arrApproved[i] = json[i].approved;
    	arrDenied[i] = json[i].denied;
    }
    
    var posR = document.getElementById("webchart");
    var posB = document.getElementById("barchart");

    // --myblue: rgb(41,128,185);
    // --cyanide: rgb(52,152,219);
    // --cloudyblue: rgb(236,240,241);
    // --juicyorange: rgb(231,76,60);
    // --block: rgb(44,62,80);

    var data = {
        labels: arrNames,
        datasets: [{
            label: "Approved",
            fill: true,
            backgroundColor: "rgba(52,152,219,1)",
            borderColor: "rgba(52,152,219,1)",
            pointBackgroundColor: "rgba(52,152,219,1)",
            data: arrApproved
        }, {
            label: "Denied",
            fill: true,
            backgroundColor: "rgba(231,76,60,1)",
            borderColor: "rgba(231,76,60,1)",
            pointBackgroundColor: "rgba(231,76,60,1)",
            data: arrDenied
        }, {
            label: "Pending",
            fill: true,
            backgroundColor: "rgba(44,62,80,1)",
            borderColor: "rgba(44,62,80,1)",
            pointBackgroundColor: "rgba(44,62,80,1)",
            data: arrPending
        }]
    };

    var app = 0;
    var den = 0;
    var pen = 0;

    for (val in data.datasets["0"].data)
        app += data.datasets["0"].data[val];

    for (val in data.datasets["1"].data)
        den += data.datasets["1"].data[val];

    for (val in data.datasets["2"].data)
        pen += data.datasets["2"].data[val];

    //alert("approved: " + app + ", denied: " + den + ", pending: " + pen);

    var sumdata = {
        labels: ['Approved','Denied','Pending'],
        datasets: [{
            data: [app,den,pen],
            backgroundColor: ["rgba(52,152,219,1)", "rgba(231,76,60,1)", "rgba(44,62,80,1)"],
        }]
    };

    var chartR = new Chart(posR, {
        type: 'pie',
        data: sumdata ,
        options: {
            legend: {
                display: false,
            }
        }
    });

    var chartR = new Chart(posB, {
        type: 'horizontalBar',
        data: data ,
        options: {
            legend: {
                display: true,
            },
            scales: {
                xAxes: [{
                    gridLines: {
                        offsetGridLines: false
                    },
                    ticks: {
                        //suggestedMax: Math.ceil(Math.max(...[Math.max(...arrApproved),Math.max(...arrDenied),Math.max(...arrPending)]) / 10 ) * 10
                    }
                }]
            }
        }
    });
}