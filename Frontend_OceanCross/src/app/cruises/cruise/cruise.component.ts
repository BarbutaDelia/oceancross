import { Component, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatSort } from '@angular/material/sort';
import { HomeComponent } from '../home/home.component';
import { ICruiseActivity } from '../interfaces/cruiseActivities.interface';
import { IPort } from '../interfaces/port.interface';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

const CRUISE_ACTIVITIES: ICruiseActivity[] =[
  { id: 0, date: new Date(), time: "13:00", location: "vas" },
  { id: 1, date: new Date(), time: "12:00", location: "altundeva" },
]

const PORT: IPort[] = [
  { name: "Constanta", dateOfStop: new Date(), timeOfStop: "9:00", durationOfStop: "6h" },
  { name: "Baltimore", dateOfStop: new Date(), timeOfStop: "15:30", durationOfStop: "2h 45min" }
]

@Component({
  selector: 'app-cruise',
  templateUrl: './cruise.component.html',
  styleUrls: ['./cruise.component.css']
})



export class CruiseComponent implements OnInit {
  cruise;
  cruiseID;
  login:boolean=false;
 
  displayedColumnsActivities: string[] = ['id', 'date', 'time', 'location'];
  dataSourceActivities = new MatTableDataSource(CRUISE_ACTIVITIES);
 

  displayedColumnsPorts: string[] = ['name', 'dateOfStop', 'timeOfStop', 'durationOfStop'];
  dataSourcePorts = new MatTableDataSource(PORT);
  
  @ViewChild('TableOneSort') TableOneSort = new MatSort();
  @ViewChild('TableTwoSort') TableTwoSort = new MatSort();

  //@ViewChild('paginatorFirst') paginatorFirst: MatPaginator;
  //@ViewChild('paginatorSecond') paginatorSecond: MatPaginator;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.cruiseID = this.activatedRoute.snapshot.paramMap.get('id');
    //this.cruise = this.service.cards.find(x => x.id == this.cruiseID);
  }

  ngAfterViewInit() {    
    this.dataSourceActivities.sort = this.TableOneSort;
    this.dataSourcePorts.sort = this.TableTwoSort;

    //this.dataSourceActivities.paginator = this.paginatorFirst;
  }

}
