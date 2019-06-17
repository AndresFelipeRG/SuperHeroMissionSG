import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MissionService} from '../mission/mission.service';
import {Mission} from '../mission/mission';
import { FormGroup, FormControl} from '@angular/forms';

@Component({
  selector: 'app-add-mission',
  templateUrl: './add-mission.component.html',
  styleUrls: ['./add-mission.component.css']
})
export class AddMissionComponent{
  mission: Mission;
  profileForm = new FormGroup({
    missionName: new FormControl(''),
    superHeroName: new FormControl(''),
    isCompleted: new FormControl(false)
  });
  constructor(private route: ActivatedRoute, private router: Router, private missionService: MissionService){
      this.mission = new Mission();
  }
  onSubmit(){
     this.mission.setIsDeleted(false);
     this.missionService.saveMission(this.mission).subscribe(result => this.navigateToMissions());
  }
  navigateToMissions(){
    this.router.navigate(['/missions'])
  }
}
