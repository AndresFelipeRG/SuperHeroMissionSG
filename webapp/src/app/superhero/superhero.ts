import {OnInit} from "@angular/core"
export class SuperHero implements OnInit{
  missionName: string;
  superHeroName: string;
  firstName: string;
  lastName: string;
  _missionName: string;
  _superHeroName: string;
  _firstName: string;
  _lastName: string;
  ngOnInit(): void {
      this.setFirstName('');
      this.setLastName('');
      this.setMissionName('');
      this.setSuperHeroName('');
  }
  public  _setMissionName(name: string){
    this._missionName = name;
  }
  public  _setSuperHeroName(name: string){
    this._superHeroName = name;
  }
  public  setMissionName(name: string){
    this.missionName = name;
  }
  public  setSuperHeroName(name: string){
    this.superHeroName = name;
  }
  public  _setFirstName(name: string){
    this._firstName = name;
  }
  public  _setLastName(name: string){
    this._lastName = name;
  }
  public  setFirstName(name: string){
    this.firstName = name;
  }
  public  setLastName(name: string){
    this.lastName = name;
  }

}
