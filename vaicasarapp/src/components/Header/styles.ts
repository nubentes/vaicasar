import styled from 'styled-components/native';
import { StatusBar } from 'react-native';

const height = StatusBar.currentHeight;

export const Container = styled.View`
  flex: 1;
`;

export const Img = styled.ImageBackground`
  width: 100%;
  height: 370px;
`;

export const Logo = styled.View`
  padding-top: ${height}px;
  align-items: center;
`;

export const Title = styled.Text`
  font-size: 34px;
  font-weight: regular;
  color: ${({ theme }) => theme.colors.brown};
`;

export const Info = styled.View`
  margin-top: 50px;
  align-items: center;
`;

export const Name = styled.Text`
  font-size: 30px;
  font-weight: medium;
  color: ${({ theme }) => theme.colors.black};
  margin-bottom: 15px;
`;

export const Date = styled.Text`
  font-size: 30px;
  font-weight: regular;
  color: ${({ theme }) => theme.colors.black};
`;

export const Banner = styled.View`
  position: absolute;
  bottom: 0px;

  width: 100%;
  height: 50px;

  background-color: ${({ theme }) => theme.colors.green};

  align-items: center;
  justify-content: center;
`;

export const BannerInfo = styled.Text`
  font-size: 24px;
  font-weight: regular;
  color: ${({ theme }) => theme.colors.white};
`;
