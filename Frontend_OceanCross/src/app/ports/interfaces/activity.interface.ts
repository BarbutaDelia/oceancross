import { IPortActivitySchedule } from "./port-activity-schedule";
import { IPortActivity } from "./port-activity.interface";

export interface IActivity {
    portActivitySchedule: IPortActivitySchedule,
    portActivity: IPortActivity
}