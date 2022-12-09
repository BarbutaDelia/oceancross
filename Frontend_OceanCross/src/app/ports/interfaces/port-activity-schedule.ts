import { Time } from "@angular/common";

export interface IPortActivitySchedule{
    ID: number,
    portActivityID: number,
    startDate: Date,
    startTime: Time,
    duration: number,
    location: string,
    price: number
}