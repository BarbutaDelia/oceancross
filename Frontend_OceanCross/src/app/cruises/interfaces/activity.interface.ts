import { Time } from "@angular/common";

export interface IActivity {
    ID?: number,
    activityName: String,
    startDate:Date,
    startTime: Time,
    duration: number,
    location:String
}