import { Component, Input, OnInit } from '@angular/core';
import { IPort } from '../interfaces/port.interface';

@Component({
  selector: 'app-ports',
  templateUrl: './ports.component.html',
  styleUrls: ['./ports.component.css']
})
export class PortsComponent implements OnInit {

  constructor() { }
  @Input() public ports: IPort[];
  ngOnInit(): void {

  }
  public deleteElem(port:IPort)
  {
    const index = this.ports.indexOf(port);
    this.ports.splice(index, 1);
  }

}
