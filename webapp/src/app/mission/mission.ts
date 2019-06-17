export class Mission{
  missionName: string;
  superHeroName: string;
  isCompleted: boolean;
  isDeleted: boolean;
  _missionName: string;
  _superHeroName: string;
  _isCompleted: boolean;
  _isDeleted: boolean;
  public  setIsDeleted(deleted: boolean){
    this.isDeleted = deleted;
  }
  public  setIsCompleted(completed: boolean){
    this.isCompleted= completed;
  }
  public  setMissionName(name: string){
    this.missionName = name;
  }
  public  setSuperHeroName(name: string){
    this.superHeroName = name;
  }
  public  _setIsDeleted(deleted: boolean){
    this._isDeleted = deleted;
  }
  public  _setIsCompleted(completed: boolean){
    this._isCompleted= completed;
  }
  public  _setMissionName(name: string){
    this._missionName = name;
  }
  public  _setSuperHeroName(name: string){
    this._superHeroName = name;
  }

}
