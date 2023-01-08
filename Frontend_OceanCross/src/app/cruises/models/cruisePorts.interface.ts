import { Time } from "@angular/common"

export interface ICruisePorts{
    id: number,
    name:string,
    port_id:number,
    arrival_date:string,
    arrival_time:Time,
    duration:number
}