import { MissionService } from './mission.service';
import {ActivatedRoute, Router} from '@angular/router';
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
    deleteMission(mission: Mission):void{
      this.missionService.deleteMission(mission).subscribe(
        result => this.missionService.navigateToMissions()
      );
    }

  }
