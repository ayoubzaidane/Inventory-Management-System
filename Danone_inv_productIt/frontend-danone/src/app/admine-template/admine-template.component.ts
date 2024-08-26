import { Component } from '@angular/core';

@Component({
  selector: 'app-admine-template',
  templateUrl: './admine-template.component.html',
  styleUrl: './admine-template.component.css'
})
export class AdmineTemplateComponent {
  toggleSidebar() {
    const sidebar = document.getElementById('sidebar-wrapper');
    if (sidebar) {
      sidebar.classList.toggle('show');
    }
  }

}
