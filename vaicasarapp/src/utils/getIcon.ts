import LogoSvg from '../assets/logo.svg';

export default function getAccessoryIcon(type: string) {
  switch (type) {
    case 'logo':
      return LogoSvg;
    default:
      return LogoSvg;
  }
}
