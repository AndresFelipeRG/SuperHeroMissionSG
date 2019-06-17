import { MissionService } from './mission.service';
import {Component, OnInit} from '@angular/core';
import {Mission} from './mission';

@Component({
  selector: 'app-mission',
  templateUrl: './mission.component.html',
  styleUrls: ['./mission.component.css']
})
export class MissionComponent implements OnInit{
    missions: Mission[];

    constructor(private missionService: MissionService){ }

    ngOnInit(): void {
     this.getMissions();
    }
    getMissions(): void {
      this.missionService.getAllMissions()
          .subscribe((missionData) => {
                this.missions = missionData
          })
    }
}
