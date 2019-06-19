import {ActivatedRoute, Router} from '@angular/router';
import {MissionService} from '../mission/mission.service';
import {Mission} from '../mission/mission';
import { FormGroup, FormControl} from '@angular/forms';
import { Component, OnInit, Input } from '@angular/core';
@Component({
  selector: 'app-mission-details',
  templateUrl: './mission-details.component.html',
  styleUrls: ['./mission-details.component.css']
})
export class MissionDetailsComponent implements OnInit {
  mission: Mission;
  private sub: any;
  profileForm = new FormGroup({
    _missionName: new FormControl(''),
    _superHeroName: new FormControl(''),
    _isCompleted: new FormControl()
  });
  constructor(private route: ActivatedRoute, private router: Router,  private missionService: MissionService) { }


  ngOnInit() {
    this.mission = new Mission();
    this.sub = this.route.params.subscribe(params => {

        this.mission.setIsDeleted(false);
        this.mission._setIsDeleted(false);
        this.mission.setMissionName(params['missionName']);
        this.mission.setSuperHeroName(params['superHeroName']);
        this.mission.setIsCompleted(params['isCompleted']);
        this.mission._setIsCompleted(params['isCompleted']);
    });
  }
  onSubmit(){
    this.mission.setIsDeleted(false);
    this.mission._setIsDeleted(false);
    this.missionService.updateMission(this.mission).subscribe(result =>this.navigateToMissions());
  }
  cancel(){
    this.navigateToMissions();
  }
  navigateToMissions(){
    this.router.navigate(['/missions'])
  }

}
