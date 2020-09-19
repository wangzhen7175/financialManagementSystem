
function createChart(container,title,yaxisData,chartType,height,width,unit)
{
	options = {
        chart: {
            renderTo: container,
            type: chartType,
            height:height,
            width:width
        },
        title: {
            text: title,
        },
        xAxis: {
            categories: []
        },
        yAxis: {
            min: 0,
            title: {
            	text: '花费'
            }
        },
        tooltip: {
            formatter: function() {
            	return ''+ this.key +': '+this.y + unit;
            }
        },
        plotOptions: {
        	series: {
                allowPointSelect: true
            }
        },
        series: []
    };
	
	//options.xAxis.categories = xaxisData;
	options.series = yaxisData;
	var chart = new Highcharts.Chart(options);
	return chart;
}
