// import { useState } from "react"
// import { createContext, useContext } from "react"
// import { acomodacao } from '../recomendacoes/info'

// // Ciração do Contexto
// const ShowContext = createContext()

// // Criação do Provedor para o Contexto
// export function ShowProvider(props) {

//     // State que irá controlar qual Tema a aplicação está usando


    
//     // Função responsável por Trocar o Tema
//     function changeShow() {
//             setShow(!show)
//     }

//     return(

//         // Construção dos Elementos para utilizarmos o Contexto em nossa Aplicação, tudo o que for contido no "value" será exportado e poderá ser utilizado em Componentes que utilizarem o Hook Customizado "useTheme"
//         <ShowContext.Provider value={{show, changeShow}}>
//             { props.children }
//         </ShowContext.Provider>

//     )

// }

// // Hook Personalizado que irá ser utilizado quando quisermos utilizar alguma das Funcionalidades contidas em nosso Contexto
// export function useShow() {

//     const context = useContext(ShowContext)

//     return context

// }