import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoremComponent } from './shared/components/lorem/lorem-component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, LoremComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'linuxhub-web';
}
