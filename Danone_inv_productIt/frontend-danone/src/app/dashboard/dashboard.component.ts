import { Component, OnInit, AfterViewInit } from '@angular/core';
import * as echarts from 'echarts';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit, AfterViewInit {

  constructor() {}

  ngOnInit(): void {}

  ngAfterViewInit(): void {
    this.initChart();
  }

  initChart() {
    const chartDom = document.getElementById('barChart')!;
    const myChart = echarts.init(chartDom);
    const option = {
      xAxis: {
        type: 'category',
        data: ['Casablanca', 'Rabat', 'Marrakech', 'Tangier', 'Fes']
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: [150, 80, 120, 90, 100],
        type: 'bar',
        itemStyle: {
          borderRadius: [4, 4, 0, 0],  // Add rounded corners to bars
        },
        barWidth: '60%',  // Adjust the width of the bars
      }],
      grid: {
        top: '10%',
        bottom: '10%',
        left: '3%',
        right: '3%',
        containLabel: true
      }
    };

    myChart.setOption(option);
  }
}


