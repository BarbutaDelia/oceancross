import { Time } from "@angular/common"

export interface IOnboardActivities{
    id: number,
    name:string,
    start_date: Date,
    start_time:Time,
    duration:number
    location:string
}