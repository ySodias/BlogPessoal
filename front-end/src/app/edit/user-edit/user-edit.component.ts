import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { AlertasService } from 'src/app/service/alertas.service';
import { AuthService } from 'src/app/service/auth.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  user: User = new User()
  confirmarSenha: string
  tipoUsuario: string
  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private alerta: AlertasService
  ) { }

  ngOnInit(){
    window.scroll(0,0)

    if(environment.token == ''){
      this.router.navigate(['/entrar'])
    }
      let idUser = this.route.snapshot.params['id']
      this.findByIdUser(idUser)
  }

    findByIdUser(id: number){
      this.authService.getByIdUser(id).subscribe((resp: User)=>{
      this.user = resp
    })
  }

    confirmSenha(event: any){
      this.confirmarSenha = event.target.value
    }
  
    tipoUser(event: any){
      this.tipoUsuario = event.target.value
      console.log(this.tipoUsuario)
    }
  
    atualizar(){
      this.user.tipo = this.tipoUsuario

      if(this.user.senha != this.confirmarSenha){
        this.alerta.showAlertDanger('As senhas não coincidem')
      }else{
        this.authService.cadastrar(this.user).subscribe((resp: User) => {
          this.user = resp
          this.alerta.showAlertSucess('Usuário atualizado com sucesso, faça o login novamente')
          environment.token = ''
          environment.nome = ''
          environment.foto = ''
          environment.id = 0
          this.router.navigate(['/entrar'])
        })
    }
  }
}



