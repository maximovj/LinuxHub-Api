import { Injectable } from "@angular/core";
import { environments } from "../../../environments/environments";

@Injectable({
  providedIn: "root"
})
export class DistributionsService
{
  ANGULAR_BASEURL_LINUX_DISTRIBUTIONS_API :string = environments.ANGULAR_BASEURL_LINUX_DISTRIBUTIONS_API;

}
