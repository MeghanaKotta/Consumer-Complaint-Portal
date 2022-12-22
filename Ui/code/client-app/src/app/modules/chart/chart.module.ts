import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { PieChartComponent } from './pie-chart/pie-chart.component';

@NgModule({
  imports:      [ CommonModule ],
  declarations: [ PieChartComponent ],
  exports: [ PieChartComponent ],
  bootstrap:    [  ]
})
export class ChartModule { }
