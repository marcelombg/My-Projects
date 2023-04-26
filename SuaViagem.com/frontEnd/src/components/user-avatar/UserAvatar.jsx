import React from 'react'
import './styles.css'
import { useLogin } from '../hooks/useLogin';
import { useToken } from '../hooks/useToken';
import CancelIcon from '@mui/icons-material/Cancel';
import { useNavigate, Link } from 'react-router-dom';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import EditIcon from '@mui/icons-material/Edit';
import FileCopyIcon from '@mui/icons-material/FileCopy';
import { styled, alpha } from '@mui/material/styles';
import Button from '@mui/material/Button';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';

export default function UserAvatar() {

  const navigate = useNavigate()
  const { changeLogin } = useLogin()
  const [userData, setUserData] = React.useState({})
  const [laoding, setLaoding] = React.useState(false)
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  const { token, changeToken } = useToken()


  const tokenLocalStorage = localStorage.getItem('token')

  React.useEffect(() => {
    async function fetchUserData() {
      setLaoding(true)
      try {
        const response = await fetch(`http://3.142.238.11:8081/user/${token || tokenLocalStorage}`);
        const userData = await response.json();
        const { id, nome, sobrenome, email, userRoles } = userData

        setUserData({
          id,
          nome,
          sobrenome,
          email,
          userRoles,
        });

        setLaoding(false)
        localStorage.setItem('userAvata', JSON.stringify({ nome, sobrenome }))
      } catch (error) {
      }
    }

    fetchUserData()
  }, [token, tokenLocalStorage])

  const { id, nome, sobrenome, userRoles } = userData
  const handleRemove = () => {

    localStorage.removeItem('token')
    changeToken(null)
    changeLogin(false)
   
    navigate('/');
  }

  const StyledMenu = styled((props) => (
    <Menu
      elevation={0}
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'right',
      }}
      transformOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      {...props}
    />
  ))(({ theme }) => ({
    '& .MuiPaper-root': {
      borderRadius: 6,
      marginTop: theme.spacing(1),
      minWidth: 180,
      color:
        theme.palette.mode === 'light' ? 'rgb(55, 65, 81)' : theme.palette.grey[300],
      boxShadow:
        'rgb(255, 255, 255) 0px 0px 0px 0px, rgba(0, 0, 0, 0.05) 0px 0px 0px 1px, rgba(0, 0, 0, 0.1) 0px 10px 15px -3px, rgba(0, 0, 0, 0.05) 0px 4px 6px -2px',
      '& .MuiMenu-list': {
        padding: '4px 0',
      },
      '& .MuiMenuItem-root': {
        '& .MuiSvgIcon-root': {
          fontSize: 18,
          color: theme.palette.text.secondary,
          marginRight: theme.spacing(1.5),
        },
        '&:active': {
          backgroundColor: alpha(
            theme.palette.primary.main,
            theme.palette.action.selectedOpacity,
          ),
        },
      },
    },
  }));

  return (
    <>
      {
        laoding ?
          <span className='loading-avatar'>loading...</span>
          :
          <div className='avatar-container'>

            <div className='avatar-image'>
              {nome?.charAt(0).toUpperCase()}
              {sobrenome?.charAt(0).toUpperCase()}
            </div>

            <div className='avatar-text'>
              <span>Olá</span>
              <p>
                {nome} {sobrenome}
              </p>
            </div>
            <div>
            {userRoles === 'ROLE_ADMIN' && 
            <Link style={{ textDecoration: 'none', color: '#0F5EA2', fontWeight: '700' }} to='administracao'>
              <Button
                id="demo-customized-button"
                aria-controls={open ? 'demo-customized-menu' : undefined}
                aria-haspopup="true"
                aria-expanded={open ? 'true' : undefined}
                variant="contained"
                disableElevation
                onClick={handleClick}
                size='small'
                sx={{
                  background: '#0F5EA2'
                }}
              >
                ADMIN  
              </Button>
              </Link>
              }
              {
                userRoles === 'ROLE_USER' &&
                <Link to={`/${id}/reservas`} >
                  <Button
                id="demo-customized-button"
                aria-controls={open ? 'demo-customized-menu' : undefined}
                aria-haspopup="true"
                aria-expanded={open ? 'true' : undefined}
                variant="contained"
                disableElevation
                onClick={handleClick}
                size='small'
                sx={{
                  background: '#0F5EA2'
                }}
              >
                    Reservas
                  </Button>
                </Link>
                
}
            </div>
            <div className='avatar-close'>
              <CancelIcon onClick={handleRemove} sx={{
                fontSize: '20px',
                color: "#0F5EA2"
              }} />
            </div>
          </div>
      }
    </>
  )
}