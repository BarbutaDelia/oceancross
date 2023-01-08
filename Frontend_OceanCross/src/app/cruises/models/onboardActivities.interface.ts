import { Time } from "@angular/common"

export interface IOnboardActivities{
    id: number,
    name:string,
    start_date: string,
    start_time:Time,
    duration:number
    location:string
}