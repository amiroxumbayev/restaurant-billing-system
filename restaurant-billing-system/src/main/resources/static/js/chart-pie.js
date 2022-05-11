// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Pie Chart Example
var ctx = document.getElementById("pieChart");
var myPieChart = new Chart(ctx, {
  type: 'pie',
  data: {
    labels: ["Dessert", "Main", "Salad", "Soup", "Appetizer"],
    datasets: [{
      data: [types.DESSERT, types.MAIN, types.SALAD, types.SOUP, types.APPETIZER],
      backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745', '#413254'],
    }],
  },
});
