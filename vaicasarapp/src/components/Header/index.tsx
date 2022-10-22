import React from 'react';
import Icon from '../../assets/logo.svg';
import {
  Logo,
  Img,
  Title,
  Name,
  Date,
  Info,
  Banner,
  BannerInfo,
} from './styles';

interface HeaderProps {
  title: string;
  color: string;
}

export default function Header({ color, title }: HeaderProps) {
  return (
    <Img
      source={{
        uri: 'https://images.unsplash.com/photo-1501901609772-df0848060b33?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDE0fHx8ZW58MHx8fHw%3D&w=1000&q=80',
      }}
      imageStyle={{ opacity: 0.5 }}
    >
      <Logo>
        <Icon width={51.91} height={55.13} />

        <Title>Vai Casar?!</Title>
      </Logo>

      <Info>
        <Name>Ramon</Name>

        <Date>15 de Maio de 2023</Date>
      </Info>

      <Banner color={color}>
        <BannerInfo>{title}</BannerInfo>
      </Banner>
    </Img>
  );
}
