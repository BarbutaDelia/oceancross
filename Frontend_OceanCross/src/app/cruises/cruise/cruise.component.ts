import { Component, OnInit, ViewChild} from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ICruise } from '../models/cruise.interface';
import { CruisesService } from '../services/cruises-service.service';
import { ActivatedRoute } from '@angular/router';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';

@Component({
  selector: 'app-cruise',
  templateUrl: './cruise.component.html',
  styleUrls: ['./cruise.component.css']
})

export class CruiseComponent implements OnInit {
  public cruise : ICruise;
  public cruiseID: number;

  public displayedColumnsPorts: string[] = ['name','arrival_date', 'arrival_time', 'duration'];
  public displayedColumnsActivities: string[] = ['name', 'start_date','start_time','duration', 'location'];

  public dataSourceActivities = new MatTableDataSource([]);
  public dataSourcePorts = new MatTableDataSource([])
  
  @ViewChild('TableActivities') TableActivities = new MatSort();
  @ViewChild('TablePorts') TablePorts = new MatSort();

  constructor(private activatedRoute: ActivatedRoute, private cruisesService:CruisesService, public localStorageService:LocalStorageService ) {}

  ngOnInit(): void {
    this.cruiseID = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.cruisesService.getCruise(this.cruiseID).subscribe( cruise => {
      this.cruise = cruise;
      this.dataSourcePorts.data = this.cruise.cruisePorts;
      this.dataSourceActivities.data = this.cruise.onboardActivities;
    })
  }

  ngAfterViewInit() {    
    this.dataSourceActivities.sort = this.TableActivities;
    this.dataSourcePorts.sort = this.TablePorts;
  }

}