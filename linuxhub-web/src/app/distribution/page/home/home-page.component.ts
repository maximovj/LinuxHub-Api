import { Component } from "@angular/core";
import { NavBarComponent } from "../../../shared/components/navbar/navbar-component";

@Component({
  imports: [
    NavBarComponent,
  ],
  styles: ``,
  template: `
    <shared-navbar-component></shared-navbar-component>
  `,
})
export class HomePageComponent
{

  title = "Home Page";

}
