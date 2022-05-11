// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Area Chart Example
chartIt();
async function chartIt() {
    const data = await getData();
    var ctx = document.getElementById("lineChart");
    var myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: data.months,
            datasets: [{
                label: "avg",
                fill: false,
                lineTension: 0.3,
                backgroundColor: "rgba(2,117,216,0.2)",
                borderColor: "rgba(2,117,216,1)",
                pointRadius: 5,
                pointBackgroundColor: "rgba(2,117,216,1)",
                pointBorderColor: "rgba(255,255,255,0.8)",
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "rgba(2,117,216,1)",
                pointHitRadius: 50,
                pointBorderWidth: 2,
                data: data.avg,
            },
            {
                label: "max",
                fill: false,
                lineTension: 0.3,
                backgroundColor: "rgba(255,0,0,0.2)",
                borderColor: "rgba(255,0,0,1)",
                pointRadius: 5,
                pointBackgroundColor: "rgba(255,0,0,1)",
                pointBorderColor: "rgba(255,0,0,0.8)",
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "rgba(255,0,0,1)",
                pointHitRadius: 50,
                pointBorderWidth: 2,
                data: data.max,
            },
            {
                label: "min",
                fill: false,
                lineTension: 0.3,
                backgroundColor: "rgba(0,255,216,0.2)",
                borderColor: "rgba(2,255,0,1)",
                pointRadius: 5,
                pointBackgroundColor: "rgba(0,255,0,1)",
                pointBorderColor: "rgba(255,255,0,0.8)",
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "rgba(2,255,0,1)",
                pointHitRadius: 50,
                pointBorderWidth: 2,
                data: data.min,
            }],
        },
        options: {
            scales: {
                xAxes: [{
                    time: {
                        unit: 'date'
                    },
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        maxTicksLimit: 7
                    }
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        maxTicksLimit: 5
                    },
                    gridLines: {
                        color: "rgba(0, 0, 0, .125)",
                    }
                }],
            },
        }
    });
}

async function getData() {

    const map = new Map([
        [1, "January"],
        [2, "February"],
        [3, "March"],
        [4, "April"],
        [5, "May"],
        [6, "June"],
        [7, "July"],
        [8, "August"],
        [9, "September"],
        [10, "October"],
        [11, "November"],
        [12, "December"]
    ]);
    const months = [];
    const avg = [];
    const max = [];
    const min = [];
    for (let element of incomes) {
        months.push(map.get(element.month));
        avg.push(element.avg);
        max.push(element.max);
        min.push(element.min);
    }
    return { months, avg, max, min };
}
